package com.example.meetings.domain.service.impl;

import com.example.meetings.domain.model.*;
import com.example.meetings.domain.service.ValidationService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateFacilitatorIsParticipant(Meeting m, List<Participant> ps) {
        if (m.getFacilitatorId() == null) return;
        boolean ok = ps.stream().anyMatch(p -> p.getUserId().equals(m.getFacilitatorId()));
        if (!ok) throw new IllegalArgumentException("Facilitator must be invited");
    }

    @Override
    public void validateUniqueParticipants(List<Participant> ps) {
        Set<String> seen = new HashSet<>();
        for (Participant p : ps) {
            String key = p.getMeetingId() + "#" + p.getUserId();
            if (!seen.add(key)) throw new IllegalArgumentException("Duplicate participant: " + key);
        }
    }

    @Override
    public void validateActionItemDueDate(ActionItem item, Meeting meeting) {
        if (item.getDueDate().isBefore(meeting.getScheduledAt().toLocalDate()))
            throw new IllegalArgumentException("dueDate must be >= meeting.scheduledAt");
    }
}
