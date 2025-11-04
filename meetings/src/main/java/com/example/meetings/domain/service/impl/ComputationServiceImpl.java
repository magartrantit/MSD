package com.example.meetings.domain.service.impl;

import com.example.meetings.domain.model.*;
import com.example.meetings.domain.model.enums.*;
import com.example.meetings.domain.service.ComputationService;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.List;

@Service
public class ComputationServiceImpl implements ComputationService {
    public LocalDateTime computeEndAt(Meeting m){
        return m.getScheduledAt().plusMinutes(m.getDurationMinutes());
    }
    public ItemStatus computeOverdueStatus(ActionItem ai, LocalDate today){
        if (ai.getStatus() == ItemStatus.DONE) return ItemStatus.DONE;
        return today.isAfter(ai.getDueDate()) ? ItemStatus.OVERDUE : ItemStatus.OPEN;
    }
    public double computeAttendanceRate(List<Participant> ps){
        if (ps.isEmpty()) return 0.0;
        long attended = ps.stream().filter(p -> p.getAttendance()==Attendance.ATTENDED).count();
        return attended * 1.0 / ps.size();
    }
}
