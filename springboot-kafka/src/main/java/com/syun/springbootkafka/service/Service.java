package com.syun.springbootkafka.service;

import com.syun.springbootkafka.provider.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * @description:
 * @program: springboot-kafka
 * @author: syun
 * @create: 2018-08-14 14:02
 */
@Component
public class Service {

    private Logger logger = LoggerFactory.getLogger(Service.class);

    @Autowired
    private KafkaSender sender;


    @Scheduled(initialDelay = 1000 * 2, fixedRate = 1000 * 2)
    public void resumeUpdate() {
        sender.send();
    }
}
