package com.cason.demo.job;

import com.cason.demo.intercept.auth.AuthenticateIntercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * Created by jingle.huang on 2017/4/17.
 */
@Controller
public class DemoTask {
    private final Logger logger = LoggerFactory.getLogger(AuthenticateIntercept.class);

    /**
     * 每5分钟来一发
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void testTask(){

        logger.info("开job lo， do something---------------");

    }
}
