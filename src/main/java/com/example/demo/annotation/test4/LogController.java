package com.example.demo.annotation.test4;


import org.springframework.stereotype.Component;

@Component
public class LogController {

    @MyLog(desc = "这是结合spring aop知识，讲解自定义注解应用的一个案例")
    public void testLogAspect(){
        System.out.println("这里随便来点啥");
    }
}


