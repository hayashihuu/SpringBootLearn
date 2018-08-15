package com.syun.springboottest08.Dao;

import org.springframework.beans.factory.annotation.Autowired;


/*
 * @description:
 * @program: springboottest08
 * @author: syun
 * @create: 2018-08-15 09:24
 */

public class conrtroller {

    @Autowired
    private AsyncTasks asyncTasks;


    public void test() {
        asyncTasks.task01();
        asyncTasks.task02();
        asyncTasks.task03();
    }

}
