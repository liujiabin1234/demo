package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.dto.LargeCount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LargeCountDao {

    LargeCount queryById(Integer id);
}
