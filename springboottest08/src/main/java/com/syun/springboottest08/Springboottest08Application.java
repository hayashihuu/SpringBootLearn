package com.syun.springboottest08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @EnbaleScheduling 开启调度任务
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Springboottest08Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboottest08Application.class, args);
    }
}
