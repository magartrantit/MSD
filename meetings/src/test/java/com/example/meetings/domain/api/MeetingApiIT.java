package com.example.meetings.domain.api;

import com.example.meetings.api.dto.*;
import com.example.meetings.domain.model.enums.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeetingApiIT {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void full_flow_create_addParticipants_start_complete_get() {
        // 1) create
        CreateMeetingRequest create = new CreateMeetingRequest();
        create.setTeamId("t1");
        create.setType(MeetingType.PLANNING);
        create.setTitle("Planning");
        create.setDescription("Sprint planning");
        create.setScheduledAt(LocalDateTime.now().plusDays(1));
        create.setDurationMinutes(60);
        create.setFacilitatorId("u1");

        ResponseEntity<MeetingResponse> createdResp =
                rest.postForEntity("/api/meetings", create, MeetingResponse.class);

        assertEquals(HttpStatus.CREATED, createdResp.getStatusCode());
        assertNotNull(createdResp.getBody());
        String meetingId = createdResp.getBody().getId();

        // 2) add participants
        AddParticipantsRequest addReq = new AddParticipantsRequest(
                List.of(new ParticipantRequest("u1", Attendance.INVITED))
        );

        ResponseEntity<MeetingResponse> afterAdd =
                rest.postForEntity("/api/meetings/" + meetingId + "/participants", addReq, MeetingResponse.class);

        assertEquals(HttpStatus.OK, afterAdd.getStatusCode());
        assertNotNull(afterAdd.getBody());
        assertEquals(1, afterAdd.getBody().getParticipants().size());

        // 3) start
        StartMeetingRequest startReq = new StartMeetingRequest("u1");
        MeetingResponse started =
                rest.postForObject("/api/meetings/" + meetingId + "/start", startReq, MeetingResponse.class);

        assertNotNull(started);
        assertEquals(MeetingStatus.IN_PROGRESS, started.getStatus());

        // 4) complete
        CompleteMeetingRequest completeReq = new CompleteMeetingRequest(
                List.of(new TopicRequest("Build speed", "Discuss improvements")),
                List.of(new DecisionRequest("Continue with plan"))
        );

        MeetingResponse completed =
                rest.postForObject("/api/meetings/" + meetingId + "/complete", completeReq, MeetingResponse.class);

        assertNotNull(completed);
        assertEquals(MeetingStatus.COMPLETED, completed.getStatus());
        assertEquals(1, completed.getTopics().size());
        assertEquals(1, completed.getDecisions().size());

        // 5) get
        ResponseEntity<MeetingResponse> getResp =
                rest.getForEntity("/api/meetings/" + meetingId, MeetingResponse.class);

        assertEquals(HttpStatus.OK, getResp.getStatusCode());
        assertNotNull(getResp.getBody());
        assertEquals(meetingId, getResp.getBody().getId());
    }
}
