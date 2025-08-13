package com.huahua.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Test;

public class Activiti7Test {


/*
* 获取ProcessEngin对象的第一种方式
*RuntimeService 运行相关；  HistoryService 历史相关； TaskService 任务相关 ；RepositoryService资源相关
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
            String deleteDeploymentId = "1";
            repositoryService.deleteDeployment(deleteDeploymentId,true);
            System.out.println("部署Id为:"+deleteDeploymentId+"，已经删除成功！");
        }catch (Exception e){
            System.err.println("删除部署失败，因为部署ID不存在");
        }


        // 3. 操作 资源服务RepositoryService进行部署,
        DeploymentBuilder deployment = repositoryService.createDeployment();
        Deployment first_activiti_bpmn = deployment.addClasspathResource("bpmn/activiti_study_01.bpmn").name("first_activiti").deploy();
        //4.部署完成
        System.out.println("部署成功：："+first_activiti_bpmn.getName() +"部署ID::"+first_activiti_bpmn.getId());
    }
}
