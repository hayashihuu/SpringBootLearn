package com.syun.springbootactivemq.jms.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * @description:
 * @program: springboot-activemq
 * @author: syun
 * @create: 2018-10-22 14:36
 */
@Component
@Slf4j
public class Consumer2 {

    @JmsListener(destination = "mytest.queue")
    @SendTo("out.queue")  //将return的值发送到out.queue队列中
    public String receiveQueue(String text) {
        log.info("收到的报文为: {}", text);
        return "return message" + text;
    }

}
