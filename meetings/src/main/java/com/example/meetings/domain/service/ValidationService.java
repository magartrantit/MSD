package com.example.meetings.domain.service;

import com.example.meetings.domain.model.*;
import java.util.List;

public interface ValidationService {
    void validateFacilitatorIsParticipant(Meeting meeting, List<Participant> participants);
    void validateUniqueParticipants(List<Participant> participants);
    void validateActionItemDueDate(ActionItem item, Meeting meeting);
}
