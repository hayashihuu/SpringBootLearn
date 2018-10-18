package com.syun.springbootconcurrence.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @program: springboot-concurrence
 * @author: syun
 * @create: 2018-10-18 15:26
 */

@Component
@Slf4j
public class MockQueue {

    /**
     * 下单消息
     */
    private String placeOrder;

    /**
     * 订单完成消息
     */
    private String compeleteOrder;


    public String getPlaceOrder() {
        return placeOrder;
    }

    @Async
    public void setPlaceOrder(String placeOrder) {

            log.info("接到下单请求: " + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.compeleteOrder  = placeOrder;
            log.info("下单请求处理完毕: " + placeOrder);

    }

    public String getCompeleteOrder() {
        return compeleteOrder;
    }

    public void setCompeleteOrder(String compeleteOrder) {
        this.compeleteOrder = compeleteOrder;
    }

}
