package com.github.fanshuzaizai.activiti.support;

import lombok.SneakyThrows;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiangzy
 * @date 2019/12/5
 */
@Service("customTaskListener")
public class CustomTaskListener implements TaskListener {

    @Autowired
    private TaskService taskService;

    @SneakyThrows
    @Override
    public void notify(DelegateTask delegateTask) {
        HandleRecord handleRecord = delegateTask.getVariable("handleRecord", HandleRecord.class);
        Integer nextHandlerIndex = handleRecord.getNextHandlerIndex();
        System.out.println("分配任务给 " + handleRecord.getHandlers().get(nextHandlerIndex) + " id:" + delegateTask.getId());

    }
}
