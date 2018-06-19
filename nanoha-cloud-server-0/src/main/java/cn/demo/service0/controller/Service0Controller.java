package cn.demo.service0.controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Cason
 *
 */
@RestController
public class Service0Controller {
    /**
     * 用于测试ribbon 重试机制
     */
    int count = 0;

    @GetMapping("user/{userId}/{sleepSec}")
    String test(
            @PathVariable String userId,
            @PathVariable int sleepSec
    ) {
        try {
            System.out.println("hello:" + userId);
            count++;
            TimeUnit.SECONDS.sleep(sleepSec - count);
            return "hello:" + userId;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("post")
    String post(
            @RequestBody String body
    ) {
        return body;
    }

}
