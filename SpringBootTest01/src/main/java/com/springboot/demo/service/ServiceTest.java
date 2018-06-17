package com.springboot.demo.service;

import com.springboot.demo.model.city;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * 对于service层的单元测试
 * junit需要1.12及以上
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    cityService service;

    @Test
    public void TestCity(){
        city info = service.getCityInfoById(1);
        System.out.println(info.toString());
        System.out.println("test idea git");
    }

}
