package com.example.meetings.api.dto;

import com.example.meetings.domain.model.enums.MeetingStatus;
import com.example.meetings.domain.model.enums.MeetingType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingResponse {

    private String id;
    private String teamId;
    private MeetingType type;
    private String title;
    private String description;
    private LocalDateTime scheduledAt;
    private int durationMinutes;
    private MeetingStatus status;
    private String facilitatorId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;

    private List<TopicResponse> topics;
    private List<DecisionResponse> decisions;
    private List<ParticipantResponse> participants;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTeamId() { return teamId; }
    public void setTeamId(String teamId) { this.teamId = teamId; }

    public MeetingType getType() { return type; }
    public void setType(MeetingType type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public MeetingStatus getStatus() { return status; }
    public void setStatus(MeetingStatus status) { this.status = status; }

    public String getFacilitatorId() { return facilitatorId; }
    public void setFacilitatorId(String facilitatorId) { this.facilitatorId = facilitatorId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<TopicResponse> getTopics() { return topics; }
    public void setTopics(List<TopicResponse> topics) { this.topics = topics; }

    public List<DecisionResponse> getDecisions() { return decisions; }
    public void setDecisions(List<DecisionResponse> decisions) { this.decisions = decisions; }

    public List<ParticipantResponse> getParticipants() { return participants; }
    public void setParticipants(List<ParticipantResponse> participants) { this.participants = participants; }
}
