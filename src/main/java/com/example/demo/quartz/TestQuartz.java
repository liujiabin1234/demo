package com.example.demo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 静态：基于注解实现定时
 */
@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
public class TestQuartz {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0/5 * * * * ?") //每5秒执行一次
//    @Scheduled(fixedRate = 6000) //每6秒执行一次
    private void process() {
        log.info("现在时间：" + dateFormat.format(new Date()));
    }
}

