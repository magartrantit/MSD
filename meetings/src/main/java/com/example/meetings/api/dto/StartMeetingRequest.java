package com.example.meetings.api.dto;

import jakarta.validation.constraints.NotBlank;

public class StartMeetingRequest {
    @NotBlank
    private String facilitatorId;

    public StartMeetingRequest() {}
    public StartMeetingRequest(String facilitatorId) { this.facilitatorId = facilitatorId; }

    public String getFacilitatorId() { return facilitatorId; }
    public void setFacilitatorId(String facilitatorId) { this.facilitatorId = facilitatorId; }
}
