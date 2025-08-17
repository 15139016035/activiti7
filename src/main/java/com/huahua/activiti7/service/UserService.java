package com.huahua.activiti7.service;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    public String findManager(){
        return "张三";
    }

    public String findHr(){
        return "李四";
    }
}
