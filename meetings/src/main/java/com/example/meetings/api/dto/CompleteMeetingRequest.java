package com.example.meetings.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class CompleteMeetingRequest {

    @NotEmpty
    @Valid
    private List<TopicRequest> topics;

    @Valid
    private List<DecisionRequest> decisions;

    public CompleteMeetingRequest() {}

    public CompleteMeetingRequest(List<TopicRequest> topics, List<DecisionRequest> decisions) {
        this.topics = topics;
        this.decisions = decisions;
    }

    public List<TopicRequest> getTopics() { return topics; }
    public void setTopics(List<TopicRequest> topics) { this.topics = topics; }

    public List<DecisionRequest> getDecisions() { return decisions; }
    public void setDecisions(List<DecisionRequest> decisions) { this.decisions = decisions; }
}
