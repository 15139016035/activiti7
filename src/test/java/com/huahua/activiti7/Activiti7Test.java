package com.huahua.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

/**
 * *RuntimeService 运行相关；  HistoryService 历史相关； TaskService 任务相关 ；RepositoryService资源相关
 */

public class Activiti7Test {


/*
*
* 部署的测试方法；
* RepositoryService仓库服务
* */
    @Test
    public void getProcessEngineTest(){
        //1. 通过getDefaultProcessEngine 获取流程引擎对象，会resources目录下的activit.cfg.xml文件
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


        //2. 进行部署 ：
        // 2.1 资源相关 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //5.删除旧的部署 因为忘了起名字了
        try{
            String deleteDeploymentId = "2501";
            repositoryService.deleteDeployment(deleteDeploymentId,true);
            System.out.println("部署Id为:"+deleteDeploymentId+"，已经删除成功！");
        }catch (Exception e){
            System.err.println("删除部署失败，因为部署ID不存在");
        }


        // 3. 操作 资源服务RepositoryService进行部署,
        DeploymentBuilder deployment = repositoryService.createDeployment();
        Deployment first_activiti_bpmn = deployment.addClasspathResource("bpmn/my_one.bpmn").name("my_deployment_one_name").deploy();
        //4.部署完成
        System.out.println("部署成功：："+first_activiti_bpmn.getName() +"部署ID::"+first_activiti_bpmn.getId());
    }


    /**
     * 依据流程定义创建实例，并且启动实例
     * RuntimeService 运行时服务
     */
    @Test
    public void runInstanceTest(){

        //1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();


        //2.通过流程引擎获取RuntimeService
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();


        //3.操作runtimeService的APi来进行实例的创建与启动, 并且拿到实例对象
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my_process_one_id");

        runtimeService.setProcessInstanceName(processInstance.getId(),"my_instance_one_id");


        // 重新查询最新的实例
        processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();

        System.out.println("流程实例的名字:"+processInstance.getName());
        System.out.println("流程实例的id:"+processInstance.getId());
        System.out.println("流程实例的BusinessKey:"+processInstance.getBusinessKey());
    }
}
