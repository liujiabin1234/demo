package com.example.demo;

import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LargeCountService largeCountService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test3() {
        //1、通过redisTemplate设置值
        redisTemplate.boundValueOps("StringKey").set("StringValue");
        redisTemplate.boundValueOps("StringKey").set("StringValue", 1, TimeUnit.MINUTES);

        //2、通过BoundValueOperations设置值
        BoundValueOperations stringKey = redisTemplate.boundValueOps("StringKey");
        stringKey.set("StringVaule");
        stringKey.set("StringValue", 1, TimeUnit.MINUTES);

        //3、通过ValueOperations设置值
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("StringKey", "StringVaule");
        ops.set("StringValue", "StringVaule", 1, TimeUnit.MINUTES);

    }

    @Test
    public void contextLoads() {
        String str = "Java string-split#test";
        String[] split = str.split(" |-|#");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @Test
    public void queryLargeCountByIdTest() {
        LargeCount largeCount = largeCountService.queryById(1);
        System.out.println(largeCount);
    }
}
