package com.syun.springboottest04.Dao;

import com.syun.springboottest04.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/*
 * @description:
 * @program: springboottest04
 * @author: syun
 * @create: 2018-08-16 14:09
 */


@Controller
public class ApplicationTests {

    private final StringRedisTemplate stringRedisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate<String, User> redisTemplate;

    private final RedisDao redisDao;

    @Autowired
    public ApplicationTests(StringRedisTemplate stringRedisTemplate, RedisTemplate<String, User> redisTemplate, RedisDao redisDao) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
        this.redisDao = redisDao;
    }


    @RequestMapping("test01")
    @ResponseBody
    public String test() throws Exception {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        logger.info(stringRedisTemplate.opsForValue().get("aaa"));
        return stringRedisTemplate.opsForValue().get("aaa");
    }

    @RequestMapping("/test02")
    @ResponseBody
    public List<Object> test01() {
        // 保存对象
        User user = new User("超人", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("蜘蛛侠", 40);
        redisTemplate.opsForValue().set(user.getUsername(), user);
//        logger.info(redisTemplate.opsForValue().get("超人").getAge().longValue());
//        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
//        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
        return Arrays.asList(redisTemplate.opsForValue().get("超人"),
                redisTemplate.opsForValue().get("蝙蝠侠"),
                redisTemplate.opsForValue().get("蜘蛛侠"));

    }


    @RequestMapping("/testredis")
    @ResponseBody
    public String test02() {
        System.out.println("testtestredis");
        redisDao.List();
        return "success";

    }

    @RequestMapping("/test03")
    @ResponseBody
    public String test03(){
        redisDao.hash();
        return "hash";
    }

}
