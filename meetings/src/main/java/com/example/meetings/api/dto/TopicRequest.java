package com.example.meetings.api.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicRequest {
    @NotBlank
    private String title;

    private String notes;

    public TopicRequest() {}
    public TopicRequest(String title, String notes) {
        this.title = title; this.notes = notes;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
