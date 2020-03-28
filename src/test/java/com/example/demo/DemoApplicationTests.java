package com.example.demo;

import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LargeCountService largeCountService;

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
