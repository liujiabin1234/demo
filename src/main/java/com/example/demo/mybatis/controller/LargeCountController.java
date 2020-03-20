package com.example.demo.mybatis.controller;

import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LargeCountController {

    @Autowired
    private LargeCountService largeCountService;

    @Test
    public void queryLargeCountById() {
        LargeCount largeCount = largeCountService.queryById(1);
        System.out.println(largeCount);
    }
}
