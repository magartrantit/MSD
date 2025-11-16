package com.example.meetings.domain.repository;

import com.example.meetings.domain.model.*;
import com.example.meetings.domain.model.enums.MeetingStatus;
import com.example.meetings.domain.model.enums.MeetingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MeetingRepositoryTest {

    @Autowired
    private MeetingRepository meetingRepository;

    @Test
    void saveMeetingWithTopicsAndDecisions() {
        // root
        Meeting meeting = new Meeting();
        meeting.setId(UUID.randomUUID().toString());
        meeting.setTeamId("team-1");
        meeting.setType(MeetingType.DAILY);
        meeting.setTitle("Daily Scrum");
        meeting.setScheduledAt(LocalDateTime.now().plusDays(1));
        meeting.setDurationMinutes(15);
        meeting.setStatus(MeetingStatus.SCHEDULED);

        // component: topic
        Topic topic = new Topic();
        topic.setId(UUID.randomUUID().toString());
        topic.setTitle("Update on tasks");
        meeting.addTopic(topic);

        // component: decision
        Decision decision = new Decision();
        decision.setId(UUID.randomUUID().toString());
        decision.setDescription("Continue with current plan");
        meeting.addDecision(decision);

        // save aggregate root
        meetingRepository.save(meeting);

        List<Meeting> found = meetingRepository.findByTeamId("team-1");
        assertThat(found).hasSize(1);
        Meeting loaded = found.get(0);

        assertThat(loaded.getTopics()).hasSize(1);
        assertThat(loaded.getDecisions()).hasSize(1);
    }
}
