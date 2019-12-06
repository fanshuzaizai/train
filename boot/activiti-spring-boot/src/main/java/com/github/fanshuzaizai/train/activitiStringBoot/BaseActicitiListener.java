package com.github.fanshuzaizai.train.activitiStringBoot;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BaseActicitiListener implements ActivitiEventListener {
    @Override
    public void onEvent(ActivitiEvent event) {
        switch (event.getType()) {
            case PROCESS_STARTED:
//                whenProcessStart(event);
                break;
            case PROCESS_COMPLETED:
                whenProcessEnd(event);
                break;
            case PROCESS_CANCELLED:
                whenProcessCancel(event);
                break;
            case TASK_CREATED:
                TaskEntity entity = (TaskEntity) ((ActivitiEntityEvent) event).getEntity();
                whenTaskCreated(entity);
                break;
            case TASK_COMPLETED:
                TaskEntity entity2 = (TaskEntity) ((ActivitiEntityEvent) event).getEntity();
                whenTaskCompleted(entity2);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean isFailOnException() {
        return true;
    }

    /**
     * 流程开始
     *
     * @param event
     */
    public void whenProcessStart(ActivitiEvent event) {

    }


    /**
     * 流程结束
     *
     * @param event
     */
    public void whenProcessEnd(ActivitiEvent event) {

    }


    /**
     * 流程结束
     *
     * @param event
     */
    public void whenProcessCancel(ActivitiEvent event) {

    }


    /**
     * task创建
     *
     * @param taskEntity
     */
    public void whenTaskCreated(TaskEntity taskEntity) {
        Map<String, Object> variables = taskEntity.getVariables();


    }


    /**
     * task完成
     *
     * @param taskEntity
     */
    public void whenTaskCompleted(TaskEntity taskEntity) {

    }


}