package com.example.meetings.domain.repository;

import com.example.meetings.domain.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, String> {

    List<Participant> findByMeeting_Id(String meetingId);

    List<Participant> findByUserId(String userId);
}
