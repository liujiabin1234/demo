package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.mapper.LargeCountDao;
import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class LargeCountServiceImpl implements LargeCountService {

    @Resource(name = "largeCountDao")
//    @Autowired()@Qualifier("largeCountDao")
    private LargeCountDao largeCountDao;


    @Override
    public LargeCount queryById(Integer id) {
        return largeCountDao.queryById(id);
    }
}
