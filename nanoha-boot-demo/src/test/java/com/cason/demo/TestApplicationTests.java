package com.cason.demo;

import com.cason.demo.Service.LyUserService;
import com.cason.demo.model.LyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {
	@Autowired
	LyUserService lyUserService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUser(){
		LyUser user = lyUserService.selectByPrimaryKey(1);

		System.out.println(user);
	}

	@Test
	public void testUserPage(){
		System.out.println(lyUserService.selectAllBypage(1,1));

	}

}
