package com.example.demo.mybatis.controller;

import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LargeCountController {

    @Autowired
    private LargeCountService largeCountService;


    @RequestMapping("/queryLargeCountById")
    public LargeCount queryLargeCountById() {
        LargeCount largeCount = largeCountService.queryById(1);
        System.out.println(largeCount);
        return largeCount;
    }
}
