package com.example.demo.mybatis.service;

import com.example.demo.mybatis.dto.LargeCount;

import java.util.List;

public interface LargeCountService {

    LargeCount queryById(Integer id);

    int deleteById(Integer id);

    List<LargeCount> queryAll();
}
