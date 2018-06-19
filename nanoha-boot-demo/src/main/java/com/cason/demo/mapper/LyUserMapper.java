package com.cason.demo.mapper;


import com.cason.demo.model.LyUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LyUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LyUser record);

    int insertSelective(LyUser record);

    LyUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LyUser record);

    int updateByPrimaryKey(LyUser record);

    List<LyUser> selectAll();
}