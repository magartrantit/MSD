package com.example.meetings.domain.model;

import com.example.meetings.domain.model.enums.MeetingStatus;
import com.example.meetings.domain.model.enums.MeetingType;
import jakarta.validation.constraints.*;
import java.time.*;

public class Meeting {
    @NotNull private String id;
    @NotNull private String teamId;
    @NotNull private MeetingType type;

    @NotBlank @Size(min = 3, max = 120)
    private String title;

    private String description;

    @NotNull @Future
    private LocalDateTime scheduledAt;

    @Positive
    private int durationMinutes;

    @NotNull
    private MeetingStatus status = MeetingStatus.SCHEDULED;

    private String facilitatorId;

    @NotNull private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull private LocalDateTime updatedAt = LocalDateTime.now();

    public LocalDateTime endAt() {
        return scheduledAt.plusMinutes(durationMinutes);
    }

    // getters & setters

}
