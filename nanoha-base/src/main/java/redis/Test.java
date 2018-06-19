/**  
 *Test.java         2016-9-20下午4:37:42
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Description:
 * @Author:nanoha
 * @Since:2016-9-20
 * @Version:1.0
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        RedisService redisService = (RedisService) app.getBean("redisService");
        Map entries = redisService.entries("SS1");
        System.out.println(entries);
    }
}
