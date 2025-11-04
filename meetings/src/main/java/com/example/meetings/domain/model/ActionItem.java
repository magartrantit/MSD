package com.example.meetings.domain.model;

import com.example.meetings.domain.model.enums.ItemStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ActionItem {
    @NotNull private String id;
    @NotNull private String meetingId;
    @NotNull private String ownerId;
    @NotBlank private String title;
    @NotNull private LocalDate dueDate;
    @NotNull private ItemStatus status = ItemStatus.OPEN;
    // getters/setters...
}
