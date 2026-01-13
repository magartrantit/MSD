package com.example.meetings.application;

import com.example.meetings.api.dto.*;
import com.example.meetings.domain.model.*;
import com.example.meetings.domain.repository.MeetingRepository;
import com.example.meetings.domain.service.WorkflowService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MeetingAppService {

    private final MeetingRepository meetingRepository;
    private final WorkflowService workflowService;

    public MeetingAppService(MeetingRepository meetingRepository, WorkflowService workflowService) {
        this.meetingRepository = meetingRepository;
        this.workflowService = workflowService;
    }

    public MeetingResponse create(CreateMeetingRequest req) {
        Meeting m = new Meeting();
        m.setId("1");
        m.setTeamId(req.getTeamId());
        m.setType(req.getType());
        m.setTitle(req.getTitle());
        m.setDescription(req.getDescription());
        m.setScheduledAt(req.getScheduledAt());
        m.setDurationMinutes(req.getDurationMinutes());
        m.setFacilitatorId(req.getFacilitatorId());

        Meeting saved = meetingRepository.save(m);

        // NU mai facem findWithDetailsById (care dă MultipleBagFetchException)
        // DTO-ul se construiește în tranzacție; colecțiile sunt goale la create oricum.
        return MeetingMapper.toResponse(saved);
    }

    public MeetingResponse getById(String id) {
        Meeting m = meetingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Meeting not found: " + id));

        // accesarea listelor aici e în tranzacție => se încarcă lazy fără eroare
        return MeetingMapper.toResponse(m);
    }

    public List<MeetingResponse> listAll() {
        return meetingRepository.findAll().stream()
                .map(MeetingMapper::toResponse)
                .toList();
    }

    public MeetingResponse addParticipants(String meetingId, AddParticipantsRequest req) {
        Meeting m = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new NotFoundException("Meeting not found: " + meetingId));

        for (ParticipantRequest pr : req.getParticipants()) {
            Participant p = new Participant();
            p.setId(UUID.randomUUID().toString());
            p.setUserId(pr.getUserId());
            p.setAttendance(pr.getAttendance());
            m.addParticipant(p);
        }

        Meeting saved = meetingRepository.save(m);
        return MeetingMapper.toResponse(saved);
    }

    public MeetingResponse start(String meetingId, StartMeetingRequest req) {
        Meeting m = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new NotFoundException("Meeting not found: " + meetingId));

        // va declanșa lazy load pe participants în tranzacție (ok)
        workflowService.start(m, req.getFacilitatorId(), m.getParticipants());

        Meeting saved = meetingRepository.save(m);
        return MeetingMapper.toResponse(saved);
    }

    public MeetingResponse complete(String meetingId, CompleteMeetingRequest req) {
        Meeting m = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new NotFoundException("Meeting not found: " + meetingId));

        List<Topic> topics = req.getTopics().stream().map(tr -> {
            Topic t = new Topic();
            t.setId(UUID.randomUUID().toString());
            t.setTitle(tr.getTitle());
            t.setNotes(tr.getNotes());
            t.setMeeting(m);
            return t;
        }).toList();

        List<Decision> decisions = (req.getDecisions() == null ? List.<DecisionRequest>of() : req.getDecisions())
                .stream().map(dr -> {
                    Decision d = new Decision();
                    d.setId(UUID.randomUUID().toString());
                    d.setDescription(dr.getDescription());
                    d.setMeeting(m);
                    return d;
                }).toList();

        workflowService.complete(m, topics, decisions);

        Meeting saved = meetingRepository.save(m);
        return MeetingMapper.toResponse(saved);
    }
}
