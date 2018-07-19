package com.syun.springboottest15;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.syun.springboottest15.mapper")
@EnableCaching
public class Springboottest15Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboottest15Application.class, args);
    }
}
