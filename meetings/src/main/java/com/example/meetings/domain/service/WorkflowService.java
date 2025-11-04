package com.example.meetings.domain.service;

import com.example.meetings.domain.model.*;
import java.util.List;

public interface WorkflowService {
    void start(Meeting meeting, String facilitatorId, List<Participant> participants);
    void complete(Meeting meeting, List<Topic> topics, List<Decision> decisions);
    void cancel(Meeting meeting);
}
