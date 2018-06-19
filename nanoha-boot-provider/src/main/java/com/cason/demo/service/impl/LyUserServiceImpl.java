package com.cason.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.cason.demo.mapper.LyUserMapper;
import com.cason.demo.model.LyUser;
import com.cason.demo.service.LyUserService;

/**
 * Created by jingle.huang on 2017/3/9.
 */
@Service
public class LyUserServiceImpl implements LyUserService {
	@Autowired
	LyUserMapper lyUserMapper;

	@Override
	public LyUser selectByPrimaryKey(Integer id) {
		return lyUserMapper.selectByPrimaryKey(id);
	}
}
