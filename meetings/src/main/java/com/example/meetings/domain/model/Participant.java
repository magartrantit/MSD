package com.example.meetings.domain.model;

import com.example.meetings.domain.model.enums.Attendance;
import jakarta.validation.constraints.*;

public class Participant {
    @NotNull private String id;
    @NotNull private String meetingId;
    @NotNull private String userId;
    @NotNull private Attendance attendance = Attendance.INVITED;
    // getters/setters...
}
