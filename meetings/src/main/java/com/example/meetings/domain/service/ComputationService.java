package com.example.meetings.domain.service;

import com.example.meetings.domain.model.*;
import com.example.meetings.domain.model.enums.ItemStatus;
import java.time.*;
import java.util.List;

public interface ComputationService {
    LocalDateTime computeEndAt(Meeting meeting);
    ItemStatus computeOverdueStatus(ActionItem item, LocalDate today);
    double computeAttendanceRate(List<Participant> participants);
}
