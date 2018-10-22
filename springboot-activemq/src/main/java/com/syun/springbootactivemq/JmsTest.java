package com.syun.springbootactivemq;

import com.syun.springbootactivemq.jms.Publisher;
import com.syun.springbootactivemq.jms.producer.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

/**
 * @description:
 * @program: springboot-activemq
 * @author: syun
 * @create: 2018-10-22 14:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsTest {

    @Autowired
    private Producer producer;

    @Autowired
    private Publisher publisher;

    @Test
    public void contextLoads(){

        Destination destination = new ActiveMQQueue("mytest.queue");
        for (int i = 0; i < 10; i++) {
            producer.sendMessage(destination, "my name is syun");
        }
    }

    @Test
    public void topticTest() {
        for (int i = 0; i < 10; i++) {
            publisher.publish("test.topic", "Topic Message " + i);
        }
    }


}
