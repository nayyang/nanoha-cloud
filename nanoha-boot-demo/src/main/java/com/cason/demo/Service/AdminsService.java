package com.cason.demo.Service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by jingle.huang on 2017/4/7.
 */
@Service
public class AdminsService {
	private static final Logger log = LoggerFactory.getLogger(AdminsService.class);

	// @Autowired
	// private AdminsMapper adminsMapper;

	/**
	 * 校验token
	 * 
	 * @param mobile
	 * @param token
	 * @return
	 */
	public boolean checkToken(String mobile, String token) {
		// if(StringUtils.isBlank(mobile) || StringUtils.isBlank(token) ||
		// !StringUtils.isNumeric(mobile)){
		// return false;
		// }

		// Admins admins = adminsMapper.selectByMobile(Long.valueOf(mobile));

		// if(admins == null || !token.equalsIgnoreCase(admins.getToken())){
		// return false;
		// }
		log.info("do something--------------");
		return true;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
