package com.example.meetings.domain.model;

import jakarta.validation.constraints.*;

public class Decision {
    @NotNull private String id;
    @NotNull private String meetingId;
    @NotBlank private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
