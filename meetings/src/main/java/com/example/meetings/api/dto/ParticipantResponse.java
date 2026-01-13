package com.example.meetings.api.dto;

import com.example.meetings.domain.model.enums.Attendance;

public class ParticipantResponse {
    private String id;
    private String userId;
    private Attendance attendance;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Attendance getAttendance() { return attendance; }
    public void setAttendance(Attendance attendance) { this.attendance = attendance; }
}
