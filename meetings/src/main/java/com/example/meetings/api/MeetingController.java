package com.example.meetings.api;

import com.example.meetings.api.dto.*;
import com.example.meetings.application.MeetingAppService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingAppService meetingAppService;

    public MeetingController(MeetingAppService meetingAppService) {
        this.meetingAppService = meetingAppService;
    }

    @PostMapping
    public ResponseEntity<MeetingResponse> create(@Valid @RequestBody CreateMeetingRequest req) {
        MeetingResponse created = meetingAppService.create(req);
        return ResponseEntity.created(URI.create("/api/meetings/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public MeetingResponse getById(@PathVariable String id) {
        return meetingAppService.getById(id);
    }

    @GetMapping
    public List<MeetingResponse> listAll() {
        return meetingAppService.listAll();
    }

    @PostMapping("/{id}/participants")
    public MeetingResponse addParticipants(@PathVariable String id, @Valid @RequestBody AddParticipantsRequest req) {
        return meetingAppService.addParticipants(id, req);
    }

    @PostMapping("/{id}/start")
    public MeetingResponse start(@PathVariable String id, @Valid @RequestBody StartMeetingRequest req) {
        return meetingAppService.start(id, req);
    }

    @PostMapping("/{id}/complete")
    public MeetingResponse complete(@PathVariable String id, @Valid @RequestBody CompleteMeetingRequest req) {
        return meetingAppService.complete(id, req);
    }
}
