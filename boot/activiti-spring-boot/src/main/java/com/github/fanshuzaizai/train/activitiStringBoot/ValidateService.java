package com.github.fanshuzaizai.train.activitiStringBoot;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("validateService")
public class ValidateService implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) {
        Map<String, Object> variables = execution.getVariables();
        System.out.println("执行参数验证。。");
    }
}