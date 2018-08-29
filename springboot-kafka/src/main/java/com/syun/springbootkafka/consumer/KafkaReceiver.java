package com.syun.springbootkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
 * @description:
 * @program: springboot-kafka
 * @author: syun
 * @create: 2018-08-14 13:56
 */
@Component
@Slf4j
public class KafkaReceiver {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = {"zhisheng"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            logger.info("record =" + record);
            logger.info("message =" + message);
        }

    }

    @KafkaListener(topics = {"message"})
    public void listens(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            logger.info("record =" + record);
            logger.info("message =" + message);
        }

    }
}
