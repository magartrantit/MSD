package com.example.meetings.api.dto;

import com.example.meetings.domain.model.enums.ItemStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateTaskStatusRequest {

    @NotNull
    private ItemStatus status;

    public ItemStatus getStatus() { return status; }
    public void setStatus(ItemStatus status) { this.status = status; }
}