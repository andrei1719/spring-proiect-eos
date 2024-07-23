package com.example.demo.repository;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

        @Query("SELECT t FROM Task t WHERE " +
                "(:assigned IS NULL OR t.userID = :assigned) AND " +
                "(:subject IS NULL OR t.subject = :subject) AND " +
                "(:dueDate IS NULL OR t.dueDate = :dueDate) AND " +
                "(:status IS NULL OR t.status = :status)")
        List<Task> findByAssignedAndSubjectAndDueDateAndStatus(
                @Param("assigned") Long assigned,
                @Param("subject") String subject,
                @Param("dueDate") Date dueDate,
                @Param("status") TaskStatus status,
                Sort sort);
    }

