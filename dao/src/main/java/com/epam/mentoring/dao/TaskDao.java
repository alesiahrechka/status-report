package com.epam.mentoring.dao;

import com.epam.mentoring.domain.Task;
import com.epam.mentoring.domain.TaskStatus;

import java.util.List;
import java.util.Set;

/**
 * Created by alesya on 27.02.16.
 */
public interface TaskDao {
    public List<Task> getAllTasks();

    public List<Task> getTasksByStatus(Set<TaskStatus> statuses);

    public Task getTask(String key);

    public void removeTask(String key);

    public void addTask(Task task);

    public void updateTask(Task task);
}