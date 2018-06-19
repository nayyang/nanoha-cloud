package com.cason.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cason.demo.model.LyUser;

@Mapper
public interface LyUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(LyUser record);

	int insertSelective(LyUser record);

	LyUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(LyUser record);

	int updateByPrimaryKey(LyUser record);
}