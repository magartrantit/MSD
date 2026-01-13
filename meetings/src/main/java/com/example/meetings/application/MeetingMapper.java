package com.example.meetings.application;

import com.example.meetings.api.dto.*;
import com.example.meetings.domain.model.*;

import java.util.stream.Collectors;

public class MeetingMapper {

    public static MeetingResponse toResponse(Meeting m) {
        MeetingResponse r = new MeetingResponse();
        r.setId(m.getId());
        r.setTeamId(m.getTeamId());
        r.setType(m.getType());
        r.setTitle(m.getTitle());
        r.setDescription(m.getDescription());
        r.setScheduledAt(m.getScheduledAt());
        r.setDurationMinutes(m.getDurationMinutes());
        r.setStatus(m.getStatus());
        r.setFacilitatorId(m.getFacilitatorId());
        r.setCreatedAt(m.getCreatedAt());
        r.setUpdatedAt(m.getUpdatedAt());

        r.setTopics(m.getTopics().stream().map(MeetingMapper::toTopic).collect(Collectors.toList()));
        r.setDecisions(m.getDecisions().stream().map(MeetingMapper::toDecision).collect(Collectors.toList()));
        r.setParticipants(m.getParticipants().stream().map(MeetingMapper::toParticipant).collect(Collectors.toList()));
        return r;
    }

    private static TopicResponse toTopic(Topic t) {
        TopicResponse r = new TopicResponse();
        r.setId(t.getId());
        r.setTitle(t.getTitle());
        r.setNotes(t.getNotes());
        return r;
    }

    private static DecisionResponse toDecision(Decision d) {
        DecisionResponse r = new DecisionResponse();
        r.setId(d.getId());
        r.setDescription(d.getDescription());
        return r;
    }

    private static ParticipantResponse toParticipant(Participant p) {
        ParticipantResponse r = new ParticipantResponse();
        r.setId(p.getId());
        r.setUserId(p.getUserId());
        r.setAttendance(p.getAttendance());
        return r;
    }
}
