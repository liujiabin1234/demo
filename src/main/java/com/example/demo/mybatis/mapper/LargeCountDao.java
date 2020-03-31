package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.dto.LargeCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LargeCountDao {

    LargeCount queryById(Integer id);

    Integer deleteById(Integer id);

    List<LargeCount> queryAll();
}
