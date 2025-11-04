package com.example.meetings.domain.model;

import jakarta.validation.constraints.*;

public class Decision {
    @NotNull private String id;
    @NotNull private String meetingId;
    @NotBlank private String description;
    // getters/setters...
}
