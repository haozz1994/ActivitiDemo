package com.haozz.activitidemo.init;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;

import java.util.List;

/**
 * https://www.bilibili.com/video/BV1CC4y1h7UV?from=search&seid=15272916691101421333
 *
 * @author haozhezhe@yunquna.com
 * @date 15:56 2020-08-30
 */
public class ProcessDeployment {

    private ProcessEngine processEngine;

    @Before
    public void pre() {
        //创建ProcessEngine对象
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }

    public void run() {
        //创建RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //根据流程图发布流程
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/leave.bpmn")
//                .addClasspathResource("diagram/leave.png")
                .name("请假流程").deploy();
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }

    /**
     * 启动流程
     */
//    @Test
    public void start() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //根据key启动对应的流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");
        //每启动一个流程都会生成一个对应的id,我们将Id输出
        System.out.println(processInstance.getId());
    }


    /**
     * 查询流程
     */
//    @Test
    public void pullTask() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("李四")
                .list();

        for (Task task : list) {
            System.out.println(task.getName());
            System.out.println(task.getId());
            System.out.println(task.getAssignee());
        }

    }

    /**
     * 完成任务
     */
//    @Test
    public void complete() {
        TaskService taskService = processEngine.getTaskService();
        String id = taskService.createTaskQuery().processDefinitionKey("myProcess_1").taskAssignee("张三").singleResult().getId();
        taskService.complete(id);
    }

    /**
     * 查询部署信息
     */
//    @Test
    public void searchDeployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition leave = repositoryService.createProcessDefinitionQuery().processDefinitionKey("leave").singleResult();
        System.out.println(leave.getDeploymentId());
    }

    /**
     * 删除部署
     * 根据部署ID删除部署
     */
//    @Test
    public void deleteDeployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("1");
    }


    /**
     * 删除部署
     * 根据部署ID级联删除(当我们要删除的部署还有未完成的流程是是删不掉的这时候我们可以使用级联删除)
     */
//    @Test
    public void deleteDeployment1() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("1", true);
    }


    /**
     * 查询流程历史信息
     */
//    @Test
    public void getHistoryInfo() {

        HistoryService historyService = processEngine.getHistoryService();
        HistoricActivityInstanceQuery historicActivityInstanceQuery =
                historyService.createHistoricActivityInstanceQuery().processInstanceId("2501");
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceQuery.list()) {
            System.out.println("============================");
            System.out.println(historicActivityInstance.getActivityName());
            System.out.println(historicActivityInstance.getAssignee());
            System.out.println(historicActivityInstance.getActivityType());
        }

    }


    /**
     * 启动流程
     */
//    @Test
    public void start1() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //根据key启动对应的流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", "1001");
        //每启动一个流程都会生成一个对应的id,我们将Id输出
        System.out.println(processInstance.getId());
    }
}
