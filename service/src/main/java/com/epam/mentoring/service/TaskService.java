package com.epam.mentoring.service;

import com.epam.mentoring.domain.*;
import com.epam.mentoring.dao.*;


import java.util.List;
import java.util.Set;

/**
 * Created by alesya on 28.02.16.
 */
public interface TaskService {

    public List<Task> getAllTasks();

    public List<Task> getTasksByStatus(Set<TaskStatus> statuses);

    public Task getTask(String key);

    public void removeTask(String key);

    public void addTask(Task task);

    public void updateTask(Task task);

}
