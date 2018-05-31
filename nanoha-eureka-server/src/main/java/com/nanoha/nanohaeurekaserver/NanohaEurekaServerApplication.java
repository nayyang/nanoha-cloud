package com.nanoha.nanohaeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NanohaEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanohaEurekaServerApplication.class, args);
    }
}
