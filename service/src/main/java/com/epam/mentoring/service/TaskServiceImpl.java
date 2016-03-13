package com.epam.mentoring.service;

import com.epam.mentoring.dao.TaskDao;
import com.epam.mentoring.domain.Task;
import com.epam.mentoring.domain.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by alesya on 28.02.16.
 */

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskDao taskDao;

    @Override
    public List<Task> getAllTasks() {

        return taskDao.getAllTasks();
    }

    @Override
    public List<Task> getTasksByStatus(Set<TaskStatus> statuses) {
        return taskDao.getTasksByStatus(statuses);
    }

    @Override
    public Task getTask(String key) {
        return taskDao.getTask(key);
    }

    @Override
    public void removeTask(String key) {
        taskDao.removeTask(key);
    }

    @Override
    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

}
