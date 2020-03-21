package com.example.demo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("。。。创建session成功");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("。。。销毁session");
    }
}

