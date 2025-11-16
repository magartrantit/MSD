package com.example.meetings.domain.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class MeetingAuditListener {

    @PrePersist
    public void prePersist(Meeting meeting) {
        LocalDateTime now = LocalDateTime.now();
        meeting.setCreatedAt(now);
        meeting.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(Meeting meeting) {
        meeting.setUpdatedAt(LocalDateTime.now());
    }
}
