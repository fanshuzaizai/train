package com.github.fanshuzaizai.train.activitiStringBoot.controller;

import com.github.fanshuzaizai.train.activitiStringBoot.HandleRecord;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author Jiangzy
 * @date 2019/12/5
 */
@RequestMapping("/task")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("agree")
    public String agree(@RequestParam String taskId) {
        HandleRecord handleRecord = taskService.getVariable(taskId, "handleRecord", HandleRecord.class);
        handleRecord.setNextHandlerIndex(handleRecord.getNextHandlerIndex() + 1);
        Map<String, Object> map = Collections.singletonMap("handleRecord", handleRecord);
        taskService.complete(taskId, map);
        return "success";
    }

}
