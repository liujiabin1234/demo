package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.mapper.LargeCountDao;
import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LargeCountServiceImpl implements LargeCountService {

    @Autowired
    private LargeCountDao largeCountDao;


    @Override
    public LargeCount queryById(Integer id) {
        return largeCountDao.queryById(id);
    }
}
