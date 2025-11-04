package com.example.meetings.domain.service;

import com.example.meetings.domain.model.*;
import com.example.meetings.domain.model.enums.Attendance;
import com.example.meetings.domain.service.impl.ValidationServiceImpl;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {
    private final ValidationService svc = new ValidationServiceImpl();

    private Meeting meeting() {
        Meeting m = new Meeting();
        m.setId("m1");
        m.setTeamId("t1");
        m.setTitle("Retro");
        m.setScheduledAt(LocalDateTime.now().plusDays(1));
        m.setDurationMinutes(30);
        return m;
    }

    @Test void facilitator_must_be_participant() {
        Meeting m = meeting(); m.setFacilitatorId("u1");
        Participant p = new Participant(); p.setMeetingId("m1"); p.setUserId("u1"); p.setAttendance(Attendance.INVITED);
        assertDoesNotThrow(() -> svc.validateFacilitatorIsParticipant(m, List.of(p)));
        m.setFacilitatorId("uX");
        assertThrows(IllegalArgumentException.class, () -> svc.validateFacilitatorIsParticipant(m, List.of(p)));
    }

    @Test void unique_participants() {
        Participant p1 = new Participant(); p1.setMeetingId("m1"); p1.setUserId("u1");
        Participant p2 = new Participant(); p2.setMeetingId("m1"); p2.setUserId("u1");
        assertThrows(IllegalArgumentException.class, () -> svc.validateUniqueParticipants(List.of(p1,p2)));
    }

    @Test void due_date_after_meeting() {
        Meeting m = meeting();
        ActionItem ai = new ActionItem(); ai.setMeetingId("m1"); ai.setOwnerId("u1"); ai.setTitle("Task");
        ai.setDueDate(m.getScheduledAt().toLocalDate().minusDays(1));
        assertThrows(IllegalArgumentException.class, () -> svc.validateActionItemDueDate(ai, m));
    }
}
