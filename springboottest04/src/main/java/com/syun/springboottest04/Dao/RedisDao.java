package com.syun.springboottest04.Dao;

import com.syun.springboottest04.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import sun.rmi.transport.ObjectTable;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
@SuppressWarnings("unchecked")
public class RedisDao {
    private final StringRedisTemplate template;

    private final RedisTemplate redisTemplate;

    @Autowired
    private  RedisTemplate<String, User> redisTemplateObejct;

    @Autowired
    public RedisDao(StringRedisTemplate template, RedisTemplate<String, List<String>> redisTemplate) {
        this.template = template;
        this.redisTemplate = redisTemplate;
    }

    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        //1分钟过期
        ops.set(key,value,1, TimeUnit.MINUTES);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

    /**
     * 测试list
     */
    public void List(){

        List<String> list = new ArrayList<>();
        list.add("this ");
        list.add("is ");
        list.add("lists");
//        redisTemplate.opsForList().rightPushAll("list1", list);
        redisTemplate.opsForList().rightPushAll("list1",list);
        System.out.println(redisTemplate.opsForList().range("list1", 0, 10));
//        redisTemplate.delete("list1");
    }

    /**
     * 存储实体类
     * @return
     */
    public List<Object> object(){
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

    /**
     * 测试set
     */
    public void set(){
        Set<String> set = new HashSet<>();
        set.add("set1");
        set.add("set2");
        set.add("set3");
        SetOperations ops = redisTemplate.opsForSet();
        ops.add("set", set);
        System.out.println("redis set:" + ops.members("set"));
    }

    /**
     *测试hash
     */
    public void hash(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "syun");
        map.put("age", 11);
        HashOperations ops = redisTemplate.opsForHash();
        ops.putAll("syun", map);
        System.out.println("redis hash: " + ops.entries("syun"));
    }


}
