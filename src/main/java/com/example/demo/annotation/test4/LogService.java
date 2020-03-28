package com.example.demo.annotation.test4;


import org.springframework.stereotype.Component;

@Component
public class LogService {

    @MyLog(desc = "使用自定义注解实现日志记录的一个小案例")
    public void testLogAspect(){
        System.out.println("自定义注解小案例演示结束");
    }
}


