package com.example.meetings.api.dto;

import jakarta.validation.constraints.NotBlank;

public class DecisionRequest {
    @NotBlank
    private String description;

    public DecisionRequest() {}
    public DecisionRequest(String description) { this.description = description; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
