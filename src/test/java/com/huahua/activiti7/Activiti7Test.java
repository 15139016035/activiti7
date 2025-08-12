package com.huahua.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;

public class Activiti7Test {


/*
* 获取ProcessEngin对象的第一种方式
*
* */
    @Test
    public void getProcessEngineTest(){
        //通过getDefaultProcessEngine 获取流程引擎对象，会resources目录下的activit.cfg.xml文件
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
}
