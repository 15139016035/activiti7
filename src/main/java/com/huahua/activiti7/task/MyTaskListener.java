package com.huahua.activiti7.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component("myTaskListener")  // 注意这里的名字要和 delegateExpression 保持一致
public class MyTaskListener implements TaskListener {

    /**
     * DelegateTask 是 Activiti 在触发 任务相关事件（create / assignment / complete / delete） 时，传递进监听器的一个上下文对象。
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName(); // 事件类型
        String taskId = delegateTask.getId();
        String taskName = delegateTask.getName();
        String assignee = delegateTask.getAssignee();

        switch (eventName) {
            case "create":
                System.out.println("【监听器】任务创建: " + taskName + " (ID=" + taskId + ")");
                break;
            case "assignment":
                System.out.println("【监听器】任务分配: " + taskName + " 被分配给 " + assignee);
                break;
            case "complete":
                System.out.println("【监听器】任务完成: " + taskName);
                break;
            case "delete":
                System.out.println("【监听器】任务被删除: " + taskName);
                break;
        }
    }
}