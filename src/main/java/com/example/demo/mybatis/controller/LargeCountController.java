package com.example.demo.mybatis.controller;

import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@RestController
public class LargeCountController {

    @Autowired
    private LargeCountService largeCountService;

    @Test
    public void queryLargeCountByIdTest() {
        LargeCount largeCount = largeCountService.queryById(1);
        System.out.println(largeCount);
    }

    @RequestMapping("/queryLargeCountById")
    public LargeCount queryLargeCountById() {
        LargeCount largeCount = largeCountService.queryById(1);
        System.out.println(largeCount);
        return largeCount;
    }
}
