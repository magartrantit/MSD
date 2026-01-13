package com.example.meetings.api.dto;

import com.example.meetings.domain.model.enums.MeetingType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMeetingRequest {

    @NotBlank
    private String teamId;

    @NotNull
    private MeetingType type;

    @NotBlank
    @Size(min = 3, max = 120)
    private String title;

    @Size(max = 500)
    private String description;

    @NotNull
    @Future
    private LocalDateTime scheduledAt;

    @Positive
    private int durationMinutes;

    private String facilitatorId;

    // exemplu @JsonProperty (fără să schimbi numele în JSON)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ignoredByClient = null;

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

    public String getFacilitatorId() { return facilitatorId; }
    public void setFacilitatorId(String facilitatorId) { this.facilitatorId = facilitatorId; }
}
