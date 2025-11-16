package com.example.meetings.domain.repository;

import com.example.meetings.domain.model.ActionItem;
import com.example.meetings.domain.model.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ActionItemRepository extends JpaRepository<ActionItem, String> {

    List<ActionItem> findByMeeting_Id(String meetingId);

    List<ActionItem> findByStatus(ItemStatus status);

    List<ActionItem> findByDueDateBefore(LocalDate date);
}
