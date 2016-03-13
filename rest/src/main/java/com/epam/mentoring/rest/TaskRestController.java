package com.epam.mentoring.rest;


import com.epam.mentoring.domain.*;
import com.epam.mentoring.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by alesya on 28.02.16.
 */

@Controller
@RequestMapping("/statusreport")
public class TaskRestController {

    @Autowired
    TaskService taskService;

    @ResponseBody
    @RequestMapping(value = "/v", method = RequestMethod.GET)
    public ResponseEntity<String>  getVersion(){
        return new ResponseEntity("1", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>>  getAllTAsks(){

        List tasks = taskService.getAllTasks();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public ResponseEntity<Task> getTask(
            @RequestParam(value = "key",required=true) String key){
        Task task = taskService.getTask(key);
        return new ResponseEntity(task, HttpStatus.OK);
    }

}
