package com.huahua.activiti7;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;

/**
 * RuntimeService 运行相关；  HistoryService 历史相关； TaskService 任务相关 ；RepositoryService资源相关
 * <p>
 * 主要是操作 RepositoryService的相关数据
 */

public class activiti1RepositoryServiceTest {


    /*
     *
     * 部署
     * RepositoryService仓库服务
     * */


    @Test
    public void getProcessEngineTest() {
        //1. 通过getDefaultProcessEngine 获取流程引擎对象，会resources目录下的activit.cfg.xml文件
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


        //2. 进行部署 ：
        // 2.1 资源相关 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();


        // 3. 操作 资源服务RepositoryService进行部署,
        DeploymentBuilder deployment = repositoryService.createDeployment();

        Deployment first_activiti_bpmn = deployment.addClasspathResource("bpmn/my_three.bpmn20.xml").name("my_deployment_three_name").deploy();
        //4.部署完成
        System.out.println("部署成功：：" + first_activiti_bpmn.getName() + "部署ID::" + first_activiti_bpmn.getId());
    }


    /*
     *
     * 查询所有的部署
     * RepositoryService仓库服务
     * */


    @Test
    public void queryDeployTest() {
        //1. 通过getDefaultProcessEngine 获取流程引擎对象，会resources目录下的activit.cfg.xml文件
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


        //2. 进行部署 ：
        // 2.1 资源相关 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();


        //3.查询所有的流程定义
//      不要将这两个东西混为一谈，在概念上：  repositoryService.createDeploymentQuery()  查询的是部署的信息   repositoryService.createProcessDefinitionQuery()  查询的是流程定义的信息；
        for (Deployment deployment : repositoryService.createDeploymentQuery().list()) {
            System.out.println("此次部署的内部id" + deployment.getId());
            System.out.println("此次部署的名字" + deployment.getName());
        }
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        for (ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().list()) {
            System.out.println("此次流程定义的id" + processDefinition.getId());
            System.out.println("此次流程定义的名字" + processDefinition.getName());
        }
    }


    /**
     * 删除部署
     *
     */

    @Test
    public void deleteDeployTest(){
        //1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //2.获取RepositoryService
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        //3.删除流程引擎 级联删除
        repositoryService.deleteDeployment("7501",true);
    }


}



