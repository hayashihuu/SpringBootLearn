package com.syun.springbootconcurrence.listener;

import com.syun.springbootconcurrence.holder.DeferredResultHolder;
import com.syun.springbootconcurrence.queue.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @program: springboot-concurrence
 * @author: syun
 * @create: 2018-10-18 15:42
 */
@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(()->{
            while (true) {
                if (mockQueue.getCompeleteOrder() != null) {
                    String orderNumber = mockQueue.getCompeleteOrder();
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                    log.info("返回订单处理结果");
                    //将CompleteOrder设为空，表示处理成功
                    mockQueue.setCompeleteOrder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
