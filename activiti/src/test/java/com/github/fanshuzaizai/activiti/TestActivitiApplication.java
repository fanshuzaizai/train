package com.github.fanshuzaizai.activiti;

import com.github.fanshuzaizai.activiti.support.HandleRecord;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestActivitiApplication {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    @Test
    public void test00() {
        ProcessDefinition hireProcess1 = repositoryService.getProcessDefinition("hireProcess:4:105003");
        List<Model> list = repositoryService.createModelQuery().list();
        List<Model> list1 = repositoryService.createNativeModelQuery().list();
        System.out.println(hireProcess1);
    }

    @Test
    public void test0() {


        BpmnModel hireProcess = repositoryService.getBpmnModel("hireProcess:4:105003");
        Process mainProcess = hireProcess.getMainProcess();

        List<SequenceFlow> sequenceFlows = mainProcess.findFlowElementsOfType(SequenceFlow.class);
        String conditionExpression = sequenceFlows.get(0).getConditionExpression();

        Collection<FlowElement> flowElements = mainProcess.getFlowElements();
        for (FlowElement flowElement : flowElements) {
            if (flowElement.getClass().getName().equals("org.activiti.bpmn.model.ExclusiveGateway")) {
                System.out.println();
            }
            System.out.println(String.format("id: %s ，name：%s，type：%s", flowElement.getId(), flowElement.getName(), flowElement.getClass().getName()));
        }
    }

    @Test
    public void test1() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("FinancialReportProcess.bpmn20.xml")
                .deploy();
        System.out.println(deployment);
    }

    @Test
    public void test11() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("First.bpmn20.xml")
                .deploy();
        System.out.println(deployment);
    }

    @Test
    public void test2() {
        ProcessInstance financialReport = runtimeService.startProcessInstanceByKey("hireProcess");

        List<Execution> list = runtimeService.createExecutionQuery().list();

//        Execution execution = runtimeService.createExecutionQuery().list().get(0);


        financialReport.getProcessDefinitionVersion();
        System.out.println(financialReport);
    }

    @Test
    public void test3() {

        runtimeService.suspendProcessInstanceById("122501");

    }

    @Test
    public void test4() {
//        Task task = taskService.createTaskQuery().taskId("20005").singleResult();

        taskService.complete("117511");

    }

    @Test
    public void test41() {
        taskService.addCandidateUser("115011", "123,456,789");
        taskService.claim("115011", "999");
    }


    @Test
    public void test5() {
        String taskId = "110003";
        HandleRecord handleRecord = taskService.getVariable(taskId, "handleRecord", HandleRecord.class);
        handleRecord.setNextHandlerIndex(handleRecord.getNextHandlerIndex() + 1);
        Map<String, Object> map = Collections.singletonMap("handleRecord", handleRecord);
        taskService.complete(taskId, map);

    }
}
