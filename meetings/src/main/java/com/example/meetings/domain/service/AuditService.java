package com.example.meetings.domain.service;

public interface AuditService {
    void record(String meetingId, String userId, String action, String details);
}
