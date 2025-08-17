package com.huahua.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * TaskService
 */
public class activiti3TaskServiceTest {


    /**
     * 查询待办任务
     *
     */
    @Test
    public void queryCurrentTaskTest() {
        //1.获取默认的流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //2.获取TaskService
        TaskService taskService = defaultProcessEngine.getTaskService();

        //3.通过调用TaskService的Api来查询
        List<Task> list = taskService.createTaskQuery().taskAssignee("张三").list();
        for (Task task : list) {
            System.out.println("任务名称：" + task.getName());
            System.out.println("任务的审批人" + task.getAssignee());
            System.out.println("任务的ID：" + task.getId());

        }
    }


    /**
     * 处理代办任务
     */
    @Test
    public void handleCurrentTask() {
        //1.获取默认的流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();


        //2.获取taskService
        TaskService taskService = defaultProcessEngine.getTaskService();

        //3.查询张三当前的待办任务
        List<Task> taskList = taskService.createTaskQuery().taskAssignee("张三").list();

        //4.处理任务
        for (Task task : taskList) {
            taskService.complete(task.getId()); //同意的API

        }
    }




    /**
     * 处理代办任务 的同时添加审批意见
     */
    @Test
    public void handleCurrent01Task() {
        //1.获取默认的流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();


        //2.获取taskService
        TaskService taskService = defaultProcessEngine.getTaskService();

        //3.查询张三当前的待办任务
        List<Task> taskList = taskService.createTaskQuery().taskAssignee("张三").list();

        //4.处理任务
        for (Task task : taskList) {
            /*
              4.1添加审批意见
              第一个参数： 任务的ID
              第二个参数：流程实例的ID
              第三个参数：批注的信息
             */
            taskService.addComment(task.getId(),task.getProcessInstanceId(),"一个批注的测试！！！");


            taskService.complete(task.getId()); //同意的API

        }
    }
}
