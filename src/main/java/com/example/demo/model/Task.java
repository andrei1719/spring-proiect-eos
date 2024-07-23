package com.example.demo.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "due_date")
    private java.sql.Date dueDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "id_user")
    //@ManyToOne
    //@JoinColumn(name = "id_user", nullable = false)
    private long userID;

    public Task() {

    }

    public Task(long id, String subject, Date dueDate, TaskStatus status, long userID) {
        this.id = id;
        this.subject = subject;
        this.dueDate = dueDate;
        this.status = status;
        this.userID = userID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", userID=" + userID +
                '}';
    }
}
