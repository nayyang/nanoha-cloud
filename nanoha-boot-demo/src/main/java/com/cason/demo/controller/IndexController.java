package com.cason.demo.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cason.demo.Service.LyUserService;
import com.cason.demo.config.ConfigRetriever;
import com.cason.demo.config.SettingsRetriever;
import com.cason.demo.intercept.auth.Authenticate;
import com.cason.demo.model.LyUser;

/**
 * Created by jingle.huang on 2017/3/9.
 */
@Controller
public class IndexController {
	private final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	SettingsRetriever settingsRetriever;
	@Autowired
	ConfigRetriever configRetriever;

	@Autowired
	LyUserService lyUserService;

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		LyUser fi = lyUserService.selectByPrimaryKey(1);
		model.put("time", new Date());
		model.put("message", settingsRetriever.getMessage());
		model.put("messageCN", "测试message咯");

		model.put("name", fi.getAccountname());
		log.info("model = [" + model + "]");

		return "web";// 返回的内容就是templetes下面文件的名称
	}

	@RequestMapping("/show")
	public ModelAndView show() {
		// 可以将view指定
		ModelAndView model = new ModelAndView("show");
		LyUser fi = lyUserService.selectByPrimaryKey(1);
		fi.setDescription(configRetriever.nihaoya);
		model.addObject("fi", fi);
		return model;
	}

	@Authenticate
	@RequestMapping("/welcomeNoneAuth")
	public String web(Map<String, Object> model) {
		throw new RuntimeException("出异常了，怎么处理！");
	}

}
