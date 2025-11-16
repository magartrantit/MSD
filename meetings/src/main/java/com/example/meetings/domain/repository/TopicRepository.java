package com.example.meetings.domain.repository;

import com.example.meetings.domain.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {

    List<Topic> findByMeeting_Id(String meetingId);
}
