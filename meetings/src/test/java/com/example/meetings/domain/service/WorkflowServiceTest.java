package com.example.meetings.domain.service;

import com.example.meetings.domain.model.*;
import com.example.meetings.domain.model.enums.*;
import com.example.meetings.domain.service.impl.ValidationServiceImpl;
import com.example.meetings.domain.service.impl.WorkflowServiceImpl;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WorkflowServiceTest {
    private final ValidationService validation = new ValidationServiceImpl();
    private final WorkflowService wf = new WorkflowServiceImpl(validation);

    private Meeting meetingScheduled() {
        Meeting m = new Meeting();
        m.setId("m1"); m.setTeamId("t1");
        m.setTitle("Planning"); m.setType(MeetingType.PLANNING);
        m.setScheduledAt(LocalDateTime.now().plusDays(1)); m.setDurationMinutes(60);
        m.setStatus(MeetingStatus.SCHEDULED);
        return m;
    }

    @Test void start_ok() {
        Meeting m = meetingScheduled();
        Participant p = new Participant(); p.setMeetingId("m1"); p.setUserId("u1");
        wf.start(m, "u1", List.of(p));
        assertEquals(MeetingStatus.IN_PROGRESS, m.getStatus());
    }

    @Test void start_reject_if_not_invited() {
        Meeting m = meetingScheduled();
        Participant p = new Participant(); p.setMeetingId("m1"); p.setUserId("u1");
        assertThrows(IllegalArgumentException.class, () -> wf.start(m, "uX", List.of(p)));
    }

    @Test void complete_ok_with_topic() {
        Meeting m = meetingScheduled();
        Participant p = new Participant(); p.setMeetingId("m1"); p.setUserId("u1");
        wf.start(m, "u1", List.of(p));
        Topic t = new Topic(); t.setMeetingId("m1"); t.setTitle("Build speed");
        wf.complete(m, List.of(t), List.of());
        assertEquals(MeetingStatus.COMPLETED, m.getStatus());
    }
}
