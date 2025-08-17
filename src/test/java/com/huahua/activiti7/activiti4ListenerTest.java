package com.huahua.activiti7;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class activiti4ListenerTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 节点的监听
     */
    @Test
    public void testTaskListener() {
        // 1. 部署流程
        repositoryService.createDeployment()
                .addClasspathResource("bpmn/my_listenner.bpmn20.xml")
                .name("监听器流程")
                .deploy();

        // 2. 启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my_delopy_listenner_one_id");

        // 3. 查询第一个任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();

        System.out.println("当前任务：" + task.getName());

        // 4. 完成任务，触发 complete 事件
        taskService.complete(task.getId());
    }


    /**
     * 流程的监听
     */

    @Test
    public void testExecutionListener() {
        // 1. 部署流程
        repositoryService.createDeployment()
                .addClasspathResource("bpmn/my_listener_two.bpmn20.xml")
                .name("监听器流程")
                .deploy();

        // 2.启动流程
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("my_delopy_listenner_two_id");
        System.out.println("流程已启动, 流程实例ID=" + pi.getId());

        // 查询并完成第一个任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(pi.getId())
                .singleResult();
        System.out.println("当前任务=" + task.getName());

        taskService.complete(task.getId()); // 完成任务 → 流程走向结束
    }




}
