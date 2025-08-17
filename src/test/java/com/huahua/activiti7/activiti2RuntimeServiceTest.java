package com.huahua.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;


/**
 * RuntimeService 运行时服务
 */
@SpringBootTest
public class activiti2RuntimeServiceTest {


    //这两个依赖注入是让 uel2Test使用的
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;



    /**
     * 依据流程定义创建实例，并且启动实例
     *
     */
    @Test
    public void runInstanceTest(){

        //1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();


        //2.通过流程引擎获取RuntimeService
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();


        //3.操作runtimeService的APi来进行实例的创建与启动, 并且拿到实例对象
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my_delopy_one_id");

        runtimeService.setProcessInstanceName(processInstance.getId(),"my_instance_one_id");


        // 重新查询最新的实例
        processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();

        System.out.println("流程实例的名字:"+processInstance.getName());
        System.out.println("流程实例的id:"+processInstance.getId());
        System.out.println("流程实例的BusinessKey:"+processInstance.getBusinessKey());
    }



    /**
     * businessKey的使用：让业务和流程关联起来
     *
     */
    @Test
    public void businessKeyTest(){

        //1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();


        //2.通过流程引擎获取RuntimeService
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();


        //3.操作runtimeService的APi来进行实例的创建与启动, 并且拿到实例对象  （要设置进去业务的ID）
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my_delopy_one_id","业务ID");


        //4.获取业务ID
        System.out.println("业务ID: "+processInstance.getBusinessKey());
    }

    /*
     * UEL表达式 ${} 分配负责人
     */
    @Test
    public void uel1Test(){

        //1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();


        //2.通过流程引擎获取RuntimeService
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();



        //3.启动实例
        //3.1 组装我们的UEL
        HashMap<String, Object> map = new HashMap<>();
        map.put("username1","张三");
        map.put("username2","李四");
        //3.2启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my_delopy_two_id", "业务ID", map);



        //4.简单的打印
        System.out.println("流程实例的关联业务ID："+processInstance.getBusinessKey());

    }


    /*
     * UEL表达式 #{} 分配负责人
     */
    @Test
    public void uel2Test(){

        // 启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("my_delopy_three_id");
        System.out.println("流程实例ID = " + instance.getId());

        // 查询任务
        List<Task> list = taskService.createTaskQuery().list();
        for (Task task : list) {
            System.out.println("任务：" + task.getName() + "，办理人=" + task.getAssignee());
        }

    }





}
