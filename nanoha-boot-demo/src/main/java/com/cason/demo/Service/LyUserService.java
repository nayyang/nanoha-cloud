package com.cason.demo.Service;

import com.cason.demo.mapper.LyUserMapper;
import com.cason.demo.model.LyUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jingle.huang on 2017/3/9.
 */
@Service
public class LyUserService {
    @Autowired
    LyUserMapper lyUserMapper;

    public LyUser selectByPrimaryKey(Integer id){
        return lyUserMapper.selectByPrimaryKey(id);
    }

    public PageInfo selectAllBypage(int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize,"createTime desc");
        List<LyUser> lyUserList = lyUserMapper.selectAll();
        PageInfo pageInfo = new PageInfo(lyUserList, pageSize);
        return pageInfo;

    }
}
