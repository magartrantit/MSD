package com.example.meetings.domain.repository;

import com.example.meetings.domain.model.Meeting;
import com.example.meetings.domain.model.enums.MeetingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, String> {

    @EntityGraph(attributePaths = {"topics", "decisions", "actionItems", "participants"})
    Optional<Meeting> findWithDetailsById(String id);

    List<Meeting> findByTeamId(String teamId);

    List<Meeting> findByStatus(MeetingStatus status);

    List<Meeting> findByScheduledAtBetween(LocalDateTime from, LocalDateTime to);
}
