package com.epam.mentoring.domain;

/**
 * Created by alesya on 27.02.16.
 */
public class Task {
    private String key;
    private String description;
    private Double duration;
    private TaskStatus taskStatus;

    public Task(String key, String description) {
        this.key = key;
        this.description = description;
        taskStatus = TaskStatus.NOT_STARTED;
        duration = 0.0D;
    }

    public Task(String key, String description, Double duration, TaskStatus taskStatus) {
        this.key = key;
        this.description = description;
        this.duration = duration;
        this.taskStatus = taskStatus;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "key='" + key + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", taskStatus=" + taskStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (!key.equals(task.key)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}