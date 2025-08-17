package com.huahua.activiti7.task;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component("myExecutionListener")
public class MyExecutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        String eventName = execution.getEventName(); // start / end / take
        String activityId = execution.getCurrentActivityId(); // 当前节点ID
        String processInstanceId = execution.getProcessInstanceId();

        System.out.println("【ExecutionListener】触发事件 = " + eventName
                + " | 当前节点 = " + activityId
                + " | 流程实例ID = " + processInstanceId);
    }
}