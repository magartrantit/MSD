package com.example.meetings.api.dto;

import com.example.meetings.domain.model.enums.Attendance;
import jakarta.validation.constraints.NotBlank;

public class ParticipantRequest {
    @NotBlank
    private String userId;

    private Attendance attendance = Attendance.INVITED;

    public ParticipantRequest() {}

    public ParticipantRequest(String userId, Attendance attendance) {
        this.userId = userId;
        this.attendance = attendance;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Attendance getAttendance() { return attendance; }
    public void setAttendance(Attendance attendance) { this.attendance = attendance; }
}
