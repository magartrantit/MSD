package com.example.meetings.domain.service;

import com.example.meetings.domain.model.*;
import com.example.meetings.domain.model.enums.*;
import com.example.meetings.domain.service.impl.ComputationServiceImpl;
import org.junit.jupiter.api.Test;
import java.time.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ComputationServiceTest {
    private final ComputationService svc = new ComputationServiceImpl();

    @Test void endAt_ok() {
        Meeting m = new Meeting();
        m.setScheduledAt(LocalDateTime.of(2025,1,1,10,0));
        m.setDurationMinutes(45);
        assertEquals(LocalDateTime.of(2025,1,1,10,45), svc.computeEndAt(m));
    }

    @Test void overdue_ok() {
        ActionItem ai = new ActionItem();
        ai.setDueDate(LocalDate.now().minusDays(1));
        ai.setStatus(ItemStatus.OPEN);
        assertEquals(ItemStatus.OVERDUE, svc.computeOverdueStatus(ai, LocalDate.now()));
    }

    @Test void attendanceRate_ok() {
        Participant a = new Participant(); a.setAttendance(Attendance.ATTENDED);
        Participant b = new Participant(); b.setAttendance(Attendance.INVITED);
        assertEquals(0.5, svc.computeAttendanceRate(List.of(a,b)));
    }
}
