package com.example.meetings.domain.repository;

import com.example.meetings.domain.model.Meeting;
import com.example.meetings.domain.model.enums.MeetingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, String> {

    List<Meeting> findByTeamId(String teamId);

    List<Meeting> findByStatus(MeetingStatus status);

    List<Meeting> findByScheduledAtBetween(LocalDateTime from, LocalDateTime to);
}
