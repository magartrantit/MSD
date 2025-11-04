package com.example.meetings.domain.model;

import jakarta.validation.constraints.*;

public class Topic {
    @NotNull private String id;
    @NotNull private String meetingId;
    @NotBlank private String title;
    private String notes;
    // getters/setters...
}
