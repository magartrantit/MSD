package com.example.meetings.factory;

import com.example.meetings.domain.model.Meeting;
import com.example.meetings.domain.model.enums.MeetingStatus;
import com.example.meetings.domain.model.enums.MeetingType;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class MeetingFactory {
    public Meeting schedule(String id, String teamId, MeetingType type, String title,
                            LocalDateTime scheduledAt, int durationMinutes) {
        Meeting m = new Meeting();
        m.setId(id);
        m.setTeamId(teamId);
        m.setType(type);
        m.setTitle(title);
        m.setScheduledAt(scheduledAt);
        m.setDurationMinutes(durationMinutes);
        m.setStatus(MeetingStatus.SCHEDULED);
        return m;
    }
}
