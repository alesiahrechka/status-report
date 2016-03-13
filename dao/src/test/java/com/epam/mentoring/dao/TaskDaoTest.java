package com.epam.mentoring.dao;

import com.epam.mentoring.domain.Task;
import com.epam.mentoring.domain.TaskStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by alesya on 28.02.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-dao-test.xml"})
public class TaskDaoTest {

    @Autowired
    private TaskDao taskDao;

    @Test
    public void getAllTasksTest() {
        List<Task> allTasks = taskDao.getAllTasks();
        assertNotNull(allTasks);
        assertNotEquals(0, allTasks.size());
    }

    @Test
    public void getNotStartedTasksTest() {
        Set<TaskStatus> taskStatuses = new HashSet<TaskStatus>();
        taskStatuses.add(TaskStatus.NOT_STARTED);

        List<Task> notStartedTasks = taskDao.getTasksByStatus(taskStatuses);
        assertNotNull(notStartedTasks);
        for (int i = 0; i < notStartedTasks.size(); i++) {
            assertEquals(TaskStatus.NOT_STARTED, notStartedTasks.get(i).getTaskStatus());
        }
    }

    @Test
    public void getNotCompletedTasksTest() {
        Set<TaskStatus> taskStatuses = new HashSet<TaskStatus>();
        taskStatuses.add(TaskStatus.NOT_STARTED);
        taskStatuses.add(TaskStatus.IN_PROGRESS);

        List<Task> notStartedTasks = taskDao.getTasksByStatus(taskStatuses);
        assertNotNull(notStartedTasks);
        for (int i = 0; i < notStartedTasks.size(); i++) {
            assertNotEquals(TaskStatus.COMPLETED, notStartedTasks.get(i).getTaskStatus());
        }
    }

    @Test
    public void addTasksTest() {

        List<Task> allTasks = taskDao.getAllTasks();
        int sizeBefore = allTasks.size();

        Task task = new Task("Refactor-111", "refactor method", 0.0, TaskStatus.NOT_STARTED);
        int sizeAfterExpected = sizeBefore + 1;

        if (allTasks.contains(task)) {
            sizeAfterExpected = sizeBefore;
        }

        taskDao.addTask(task);
        allTasks = taskDao.getAllTasks();
        int sizeAfter = allTasks.size();
        assertTrue(allTasks.contains(task));
        assertEquals(sizeAfterExpected, sizeAfter);
    }

    @Test
    public void removeTasksTest() {

        List<Task> allTasks = taskDao.getAllTasks();
        int sizeBefore = allTasks.size();

        Task task = new Task("BUG-524", "fix bug", 0.0, TaskStatus.NOT_STARTED);

        int sizeAfterExpected = sizeBefore;
        if (allTasks.contains(task)) {
            sizeAfterExpected = sizeBefore - 1;
        }

        taskDao.removeTask(task.getKey());

        allTasks = taskDao.getAllTasks();
        int sizeAfter = allTasks.size();
        assertFalse(allTasks.contains(task));
        assertEquals(sizeAfterExpected, sizeAfter);
    }

    @Test
    public void getTasksTest() {

        String key = "IMPL-1111";
        String descriptionExpected = "to implement new feature";
        Double durationExpected = 8.0;
        TaskStatus taskStatusExpcted = TaskStatus.COMPLETED;

        Task task = taskDao.getTask(key);
        assertNotNull(task);
        assertEquals(descriptionExpected, task.getDescription());
        assertEquals(durationExpected, task.getDuration());
        assertEquals(taskStatusExpcted, task.getTaskStatus());
    }

    @Test
    public void updateTasksTest() {

        String key = "DATA-121";
        String descriptionUpdated = "updated value";
        Double durationUpdate = 0.4D;
        TaskStatus taskStatusUpdated = TaskStatus.IN_PROGRESS;

        Task task = new Task(key, descriptionUpdated, durationUpdate, taskStatusUpdated);
        taskDao.updateTask(task);

        Task task1 = taskDao.getTask(key);
        assertEquals(descriptionUpdated, task1.getDescription());
        assertEquals(durationUpdate, task1.getDuration());
        assertEquals(taskStatusUpdated, task1.getTaskStatus());
    }
}
