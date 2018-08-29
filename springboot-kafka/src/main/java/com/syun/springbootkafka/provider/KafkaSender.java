package com.syun.springbootkafka.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.syun.springbootkafka.beans.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/*
 * @description:
 * @program: springboot-kafka
 * @author: syun
 * @create: 2018-08-14 13:54
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate1;




    private Gson gson = new GsonBuilder().create();


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        logger.info("message = {}", gson.toJson("发送数据"));
        kafkaTemplate.send("zhisheng","测试数据");

        kafkaTemplate1.send("message", "测试多个topic");


    }



}
