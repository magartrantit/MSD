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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
