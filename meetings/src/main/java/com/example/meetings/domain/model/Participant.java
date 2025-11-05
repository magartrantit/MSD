package com.example.meetings.domain.model;

import com.example.meetings.domain.model.enums.Attendance;
import jakarta.validation.constraints.*;

public class Participant {
    @NotNull private String id;
    @NotNull private String meetingId;
    @NotNull private String userId;
    @NotNull private Attendance attendance = Attendance.INVITED;


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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}
