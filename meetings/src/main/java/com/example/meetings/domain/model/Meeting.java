package com.example.meetings.domain.model;

import com.example.meetings.domain.model.enums.MeetingStatus;
import com.example.meetings.domain.model.enums.MeetingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meetings")
@EntityListeners(MeetingAuditListener.class)
public class Meeting {

    @Id
    @Column(length = 36)
    @NotNull
    private String id;

    @NotNull
    @Column(name = "team_id", nullable = false, length = 36)
    private String teamId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MeetingType type;

    @NotBlank
    @Size(min = 3, max = 120)
    @Column(nullable = false, length = 120)
    private String title;

    @Column(length = 500)
    private String description;

    @NotNull
    @Future
    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @Positive
    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MeetingStatus status = MeetingStatus.SCHEDULED;

    @Column(name = "facilitator_id", length = 36)
    private String facilitatorId;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    // ------------ RELAȚII DE AGREGAT ----------------

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Topic> topics = new ArrayList<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Decision> decisions = new ArrayList<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ActionItem> actionItems = new ArrayList<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Participant> participants = new ArrayList<>();

    // ------------ LOGICĂ EXISTENTĂ ----------------

    public LocalDateTime endAt() {
        return scheduledAt.plusMinutes(durationMinutes);
    }

    // ------------ GETTERS / SETTERS ----------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public MeetingStatus getStatus() {
        return status;
    }

    public void setStatus(MeetingStatus status) {
        this.status = status;
    }

    public String getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(String facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public List<ActionItem> getActionItems() {
        return actionItems;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    // helper methods pentru agregat (nice de arătat la prezentare)

    public void addTopic(Topic topic) {
        topic.setMeeting(this);
        this.topics.add(topic);
    }

    public void addDecision(Decision decision) {
        decision.setMeeting(this);
        this.decisions.add(decision);
    }

    public void addActionItem(ActionItem item) {
        item.setMeeting(this);
        this.actionItems.add(item);
    }

    public void addParticipant(Participant participant) {
        participant.setMeeting(this);
        this.participants.add(participant);
    }
}
