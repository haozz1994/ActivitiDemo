package com.haozz.activitidemo.init;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Before;

/**
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
                .addClasspathResource("diagram/leave.png")
                .name("请假流程").deploy();
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }
}
