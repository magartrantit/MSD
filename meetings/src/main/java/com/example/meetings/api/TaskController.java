package com.example.meetings.api;

import com.example.meetings.api.dto.*;
import com.example.meetings.application.TaskAppService;
import com.example.meetings.domain.model.enums.ItemStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskAppService taskAppService;

    public TaskController(TaskAppService taskAppService) {
        this.taskAppService = taskAppService;
    }

    // 1) Add task to a meeting
    @PostMapping("/api/meetings/{meetingId}/tasks")
    public ActionItemResponse addTask(@PathVariable String meetingId,
                                      @Valid @RequestBody AddTaskRequest req) {
        return taskAppService.addTask(meetingId, req);
    }

    // 2) List tasks for a meeting (optional filter by status)
    @GetMapping("/api/meetings/{meetingId}/tasks")
    public List<ActionItemResponse> listTasks(@PathVariable String meetingId,
                                              @RequestParam(required = false) ItemStatus status) {
        return taskAppService.listTasks(meetingId, status);
    }

    // 3) Get task by id (useful in UI)
    @GetMapping("/api/tasks/{taskId}")
    public ActionItemResponse getTaskById(@PathVariable String taskId) {
        return taskAppService.getTaskById(taskId);
    }

    // 4) Update status
    @PatchMapping("/api/tasks/{taskId}/status")
    public ActionItemResponse updateStatus(@PathVariable String taskId,
                                           @Valid @RequestBody UpdateTaskStatusRequest req) {
        return taskAppService.updateStatus(taskId, req);
    }

    // 5) Review task
    @PostMapping("/api/tasks/{taskId}/review")
    public ActionItemResponse reviewTask(@PathVariable String taskId,
                                         @Valid @RequestBody ReviewTaskRequest req) {
        return taskAppService.reviewTask(taskId, req);
    }
}