package com.github.fanshuzaizai.activiti.conf;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;

/**
 * @author Jiangzy
 * @date 2019/11/21
 */
public class CustomActivitiEventListener implements ActivitiEventListener {
    @Override
    public void onEvent(ActivitiEvent event) {

        System.out.println(event);
        if (true) {
            return;
        }

        if (event.getType() == ActivitiEventType.TASK_CREATED) {
            System.out.println(event.getType());
        }

        if (event.getType() == ActivitiEventType.PROCESS_COMPLETED) {
            System.out.println(event.getType());
        }

        if (event.getType() == ActivitiEventType.TASK_COMPLETED) {
            System.out.println(event.getType());
        }
//        System.out.println(event.getType());
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
