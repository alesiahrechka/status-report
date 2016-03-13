package com.epam.mentoring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alesya on 01.03.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-test.xml"})
public class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @Test
    public void dummyTest(){
        assertTrue(true);
    }

    @Test
    public void getTasks(){
        List tasks = taskService.getAllTasks();
        assertNotNull(tasks);
        assertNotEquals(0,tasks.size());
    }
}
