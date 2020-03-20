package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.dto.LargeCount;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LargeCountDao {

    LargeCount queryById(Integer id);
}
