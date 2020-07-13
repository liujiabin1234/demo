package com.example.demo.mybatis.controller;

import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LargeCountController {

    @Autowired
    private LargeCountService largeCountService;


    /**
     * mybatis集成测试
     * @return
     */
    @RequestMapping("/queryLargeCountById")
    public LargeCount queryLargeCountById() {
        LargeCount largeCount = largeCountService.queryById(1);
        System.out.println(largeCount);
        return largeCount;
    }

    /**
     * 存储过程调用
     * @param id
     * @return
     */
    @RequestMapping("/deleteLargeCountById/{id}")
    public int deleteLargeCountById(@PathVariable("id") Integer id) {
        int i = largeCountService.deleteById(id);
        return i;
    }
}
