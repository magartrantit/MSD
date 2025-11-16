package com.example.meetings.domain.repository;

import com.example.meetings.domain.model.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DecisionRepository extends JpaRepository<Decision, String> {

    List<Decision> findByMeeting_Id(String meetingId);
}
