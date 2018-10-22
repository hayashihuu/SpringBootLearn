package com.syun.springbootactivemq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @description: 订阅式需要对于JmsListenerContainerFactory进行一定的配置
 * @program: springboot-activemq
 * @author: syun
 * @create: 2018-10-22 15:02
 */
@Configuration
public class JmsConf {

    @Bean
    public JmsListenerContainerFactory<?> myJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }


}
