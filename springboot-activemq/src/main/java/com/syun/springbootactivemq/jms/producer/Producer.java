package com.syun.springbootactivemq.jms.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.xml.soap.Text;

/**
 * @description:
 * @program: springboot-activemq
 * @author: syun
 * @create: 2018-10-22 14:16
 */
@Service("producer")
@Slf4j
public class Producer {

    @Autowired  // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination = "out.queue")
    public void consumerMessage(String text) {
        log.info("从out.queue中获取的报文为: {}", text);
    }


}
