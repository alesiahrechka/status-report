package com.epam.mentoring.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by alesya on 27.02.16.
 */
public class TaskTest {

    @Test
    public void TaskInitTest() {
        String key = "JJ-1111";
        String description = "to do something";
        Double duration = 0.0D;
        TaskStatus taskStatus = TaskStatus.NOT_STARTED;
        Task task = new Task(key, description);
        assertEquals(task.getKey(), key);
        assertEquals(task.getDescription(), description);
        assertEquals(task.getDuration(), duration, 0.0);
        assertEquals(task.getTaskStatus(), taskStatus);
    }

    @Test
    public void TaskEqualsTest() {

        Task task1 = new Task("bug-1", "fix bug: something is wrong");
        Task updatedTask1 = task1;
        updatedTask1.setDuration(4.0);
        updatedTask1.setTaskStatus(TaskStatus.IN_PROGRESS);
        Task task2 = new Task("bug-1", "add inf to database");
        Task anotherTask = new Task("xx-impl", "implement new function");

        assertEquals(task1, task1);
        assertEquals(task1, task2);
        assertEquals(task1, updatedTask1);

        assertNotEquals(task1, anotherTask);
        assertNotEquals(updatedTask1, anotherTask);
        assertNotEquals(task2, anotherTask);
    }
}