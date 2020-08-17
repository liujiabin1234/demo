package com.example.demo.localdatetime;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class LocalDateTimeTest {

    @Test
    public void test() {
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //前一天
        LocalDateTime plus = localDateTime.plus(-1, ChronoUnit.DAYS);
        //获取当天最大时间
        LocalDateTime dt = localDateTime.with(LocalTime.MAX);
        //转化为指定时间格式
        String format = dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
        //获取当前月份(英文)
        Month month = dt.getMonth();
        System.out.println(month);
        //获取当前月份(数字)
        int value = month.getValue();
        System.out.println(value);
    }
}
