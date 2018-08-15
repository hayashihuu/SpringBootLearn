package com.syun.springboottest08.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/*
 * @description:
 * @program: springboottest08
 * @author: syun
 * @create: 2018-08-15 09:21
 */
@Component
public class AsyncTasks {


    private Logger logger = LoggerFactory.getLogger(AsyncTasks.class);

    @Async
    public void task01() {
        logger.info("开始做任务一");
    }


    public void task02() {
        logger.info("开始做任务二");
    }


    public void task03() {
        logger.info("开始做任务三");
    }



}
