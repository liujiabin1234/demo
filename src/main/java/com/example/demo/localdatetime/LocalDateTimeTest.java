package com.example.demo.localdatetime;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class LocalDateTimeTest {


    @Test
    public void test2() {
        ArrayList<String> str = Lists.newArrayList();
        str.add("aa");
        str.add("bb");
        String join = Joiner.on(",").join(str);
        System.out.println(join);
    }


    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime todayMax = localDateTime.with(LocalTime.MAX);
        LocalDateTime todayMin = localDateTime.with(LocalTime.MIN);
        final Date todayMaxDate = Date.from(todayMax.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(todayMaxDate);
    }

    /**
     * LocalDateTime转为日期
     *
     * @param localDateTime LocalDateTime
     * @return 日期
     */
    public static Date localDateTimeToDate(final LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zdt = localDateTime.atZone(zoneId);
        final Date date = Date.from(zdt.toInstant());
        return date;
    }

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
