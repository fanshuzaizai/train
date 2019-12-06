package com.github.fanshuzaizai.activiti.conf;

import com.github.fanshuzaizai.activiti.support.CustomTaskListener;
import com.github.fanshuzaizai.activiti.support.SelectHandlersService;
import com.github.fanshuzaizai.activiti.support.ValidateService;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;

@Configuration
public class ActivitiConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public ProcessEngine processEngine() {
        return before();
    }

    @Autowired
    private CustomTaskListener customTaskListener;

    @Autowired
    private SelectHandlersService selectHandlersService;

    @Autowired
    private ValidateService validateService;

    private ProcessEngine before() {
        StandaloneProcessEngineConfiguration conf = new StandaloneProcessEngineConfiguration();
        conf.setEventListeners(Collections.singletonList(new CustomActivitiEventListener()));

        HashMap<Object, Object> map = new HashMap<>();
        map.put("customTaskListener", customTaskListener);
        map.put("selectHandlersService", selectHandlersService);
        map.put("validateService", validateService);


        conf.setBeans(map);

        ProcessEngine processEngine = conf.setDataSource(dataSource)
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
                .setAsyncExecutorActivate(false)
                .buildProcessEngine();

        DynamicBpmnService dynamicBpmnService = processEngine.getDynamicBpmnService();

        return processEngine;
    }

    @Bean
    public RuntimeService runtimeService() {
        return processEngine().getRuntimeService();
    }

    @Bean
    public TaskService taskService() {
        return processEngine().getTaskService();
    }

    @Bean
    public RepositoryService repositoryService() {
        return processEngine().getRepositoryService();
    }

    @Bean
    public HistoryService historyService() {
        return processEngine().getHistoryService();
    }

    @Bean
    public ManagementService managementService() {
        return processEngine().getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService() {
        return processEngine().getDynamicBpmnService();
    }
}
