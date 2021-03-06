package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.mapper.LargeCountDao;
import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public int deleteById(Integer id) {
        return largeCountDao.deleteById(id);
    }

    @Override
    public List<LargeCount> queryAll() {
        return largeCountDao.queryAll();
    }

    @Override
    public Long devQuery(Long i) {
        return largeCountDao.devQuery(i);
    }

    @Override
    public Long devInsert(long l) {
        return largeCountDao.devInsert(l);
    }
}
