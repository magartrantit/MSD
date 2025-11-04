package com.example.meetings.domain.service.impl;

import com.example.meetings.domain.model.*;
import com.example.meetings.domain.model.enums.MeetingStatus;
import com.example.meetings.domain.service.ValidationService;
import com.example.meetings.domain.service.WorkflowService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkflowServiceImpl implements WorkflowService {
    private final ValidationService validation;
    public WorkflowServiceImpl(ValidationService validation) { this.validation = validation; }

    @Override
    public void start(Meeting m, String facilitatorId, List<Participant> ps) {
        if (m.getStatus()!=MeetingStatus.SCHEDULED) throw new IllegalStateException("Only SCHEDULED can start");
        m.setFacilitatorId(facilitatorId);
        validation.validateFacilitatorIsParticipant(m, ps);
        m.setStatus(MeetingStatus.IN_PROGRESS);
    }

    @Override
    public void complete(Meeting m, List<Topic> topics, List<Decision> decisions) {
        if (m.getStatus()!=MeetingStatus.IN_PROGRESS) throw new IllegalStateException("Only IN_PROGRESS can complete");
        boolean hasContent = (topics!=null && !topics.isEmpty()) || (decisions!=null && !decisions.isEmpty());
        if (!hasContent) throw new IllegalStateException("Need at least 1 Topic or 1 Decision");
        m.setStatus(MeetingStatus.COMPLETED);
    }

    @Override
    public void cancel(Meeting m) {
        if (m.getStatus()!=MeetingStatus.SCHEDULED) throw new IllegalStateException("Only SCHEDULED can cancel");
        m.setStatus(MeetingStatus.CANCELLED);
    }
}
