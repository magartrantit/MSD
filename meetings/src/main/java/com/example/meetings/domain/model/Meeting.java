package com.example.meetings.domain.model;

import com.example.meetings.domain.model.enums.MeetingStatus;
import com.example.meetings.domain.model.enums.MeetingType;
import jakarta.validation.constraints.*;
import java.time.*;

public class Meeting {
    @NotNull private String id;
    @NotNull private String teamId;
    @NotNull private MeetingType type;

    @NotBlank @Size(min = 3, max = 120)
    private String title;

    private String description;

    @NotNull @Future
    private LocalDateTime scheduledAt;

    @Positive
    private int durationMinutes;

    @NotNull
    private MeetingStatus status = MeetingStatus.SCHEDULED;

    private String facilitatorId;

    @NotNull private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull private LocalDateTime updatedAt = LocalDateTime.now();

    public LocalDateTime endAt() {
        return scheduledAt.plusMinutes(durationMinutes);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public MeetingStatus getStatus() {
        return status;
    }

    public void setStatus(MeetingStatus status) {
        this.status = status;
    }

    public String getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(String facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
