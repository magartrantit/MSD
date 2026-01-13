package com.example.meetings.api.dto;

public class TopicResponse {
    private String id;
    private String title;
    private String notes;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
