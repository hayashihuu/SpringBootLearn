package com.syun.springbootactivemq.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @program: springboot-activemq
 * @author: syun
 * @create: 2018-10-22 14:59
 */
@Component
@Slf4j
public class Subscriber {

    @JmsListener(destination = "test.topic",containerFactory = "myJmsListenerContainerFactory")
    public void subscribe(String text) {
        log.info("==============<<test01   收到订阅的消息: {}", text);
    }


    @JmsListener(destination = "test.topic",containerFactory = "myJmsListenerContainerFactory")
    public void subscribetest(String text) {
        //每个订阅会使用新的线程
        log.info("==============<<test02   收到订阅的消息: {}", text);
    }

}
