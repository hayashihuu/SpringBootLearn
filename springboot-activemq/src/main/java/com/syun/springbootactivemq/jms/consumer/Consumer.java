package com.syun.springbootactivemq.jms.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @program: springboot-activemq
 * @author: syun
 * @create: 2018-10-22 14:20
 */
@Component
@Slf4j
public class Consumer {


    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     * @param text 接受到消息
     */
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        log.info("收到的报文: {}", text);
    }

}
