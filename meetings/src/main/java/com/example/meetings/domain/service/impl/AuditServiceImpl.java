package com.example.meetings.domain.service.impl;

import com.example.meetings.domain.service.AuditService;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {
    @Override public void record(String meetingId, String userId, String action, String details) {
        System.out.printf("AUDIT meeting=%s user=%s action=%s details=%s%n", meetingId, userId, action, details);
    }
}
