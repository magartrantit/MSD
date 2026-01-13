package com.example.meetings.application;

import com.example.meetings.api.dto.*;
import com.example.meetings.domain.model.ActionItem;
import com.example.meetings.domain.model.Meeting;
import com.example.meetings.domain.model.enums.ItemStatus;
import com.example.meetings.domain.repository.ActionItemRepository;
import com.example.meetings.domain.repository.MeetingRepository;
import com.example.meetings.domain.service.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskAppService {

    private final MeetingRepository meetingRepository;
    private final ActionItemRepository actionItemRepository;
    private final ValidationService validationService;

    public TaskAppService(MeetingRepository meetingRepository,
                          ActionItemRepository actionItemRepository,
                          ValidationService validationService) {
        this.meetingRepository = meetingRepository;
        this.actionItemRepository = actionItemRepository;
        this.validationService = validationService;
    }

    public ActionItemResponse addTask(String meetingId, AddTaskRequest req) {
        Meeting m = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new NotFoundException("Meeting not found: " + meetingId));

        ActionItem ai = new ActionItem();
        ai.setId(UUID.randomUUID().toString());
        ai.setOwnerId(req.getOwnerId());
        ai.setTitle(req.getTitle());
        ai.setDueDate(req.getDueDate());
        ai.setStatus(ItemStatus.OPEN);

        // leagă task-ul de meeting (agregat)
        ai.setMeeting(m);

        // validare business (de ex. dueDate vs meeting date)
        validationService.validateActionItemDueDate(ai, m);

        ActionItem saved = actionItemRepository.save(ai);
        return toResponse(saved);
    }

    public List<ActionItemResponse> listTasks(String meetingId, ItemStatus status) {
        // verificare meeting existent (optional, dar ok)
        meetingRepository.findById(meetingId)
                .orElseThrow(() -> new NotFoundException("Meeting not found: " + meetingId));

        List<ActionItem> items = actionItemRepository.findByMeeting_Id(meetingId);

        if (status != null) {
            items = items.stream().filter(i -> i.getStatus() == status).toList();
        }

        return items.stream().map(this::toResponse).toList();
    }

    public ActionItemResponse updateStatus(String taskId, UpdateTaskStatusRequest req) {
        ActionItem ai = actionItemRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found: " + taskId));

        ai.setStatus(req.getStatus());
        ActionItem saved = actionItemRepository.save(ai);
        return toResponse(saved);
    }

    public ActionItemResponse reviewTask(String taskId, ReviewTaskRequest req) {
        ActionItem ai = actionItemRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found: " + taskId));

        ai.setReviewedBy(req.getReviewedBy());
        ai.setReviewNotes(req.getReviewNotes());
        ai.setReviewedAt(LocalDateTime.now());

        ActionItem saved = actionItemRepository.save(ai);
        return toResponse(saved);
    }

    public ActionItemResponse getTaskById(String taskId) {
        ActionItem ai = actionItemRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found: " + taskId));
        return toResponse(ai);
    }

    private ActionItemResponse toResponse(ActionItem ai) {
        ActionItemResponse r = new ActionItemResponse();
        r.setId(ai.getId());
        r.setMeetingId(ai.getMeeting() != null ? ai.getMeeting().getId() : null);
        r.setOwnerId(ai.getOwnerId());
        r.setTitle(ai.getTitle());
        r.setDueDate(ai.getDueDate());
        r.setStatus(ai.getStatus());
        r.setReviewedBy(ai.getReviewedBy());
        r.setReviewNotes(ai.getReviewNotes());
        r.setReviewedAt(ai.getReviewedAt());
        return r;
    }
}