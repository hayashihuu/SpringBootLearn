package com.syun.springboottest12;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.syun.springboottest12.mapper")
public class Springboottest12Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboottest12Application.class, args);
    }
}
