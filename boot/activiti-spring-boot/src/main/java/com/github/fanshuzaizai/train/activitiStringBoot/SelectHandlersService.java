package com.github.fanshuzaizai.train.activitiStringBoot;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service("selectHandlersService")
public class SelectHandlersService implements JavaDelegate {


    @Autowired
    private TaskService taskService;

    @Override
    public void execute(DelegateExecution execution) {
        Map<String, Object> variables = execution.getVariables();
        System.out.println("执行选择处理人任务");

        HandleRecord handleRecord = new HandleRecord();
        handleRecord.setHandlers(Arrays.asList(100L, 200L, 300L));
        handleRecord.setNextHandlerIndex(0);
        execution.setVariable("handleRecord", handleRecord);
    }
}