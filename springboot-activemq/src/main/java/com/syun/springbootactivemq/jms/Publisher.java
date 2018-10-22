package com.syun.springbootactivemq.jms;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @description: 发布/订阅模式
 * @program: springboot-activemq
 * @author: syun
 * @create: 2018-10-22 14:54
 */
@Service
@Slf4j
public class Publisher {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void publish(String destinationName, String message) {
        Destination destination = new ActiveMQTopic(destinationName);
        log.info("=======>发布topic消息: {}", message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }


}
