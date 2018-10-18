package com.syun.springbootconcurrence.controller;

import com.syun.springbootconcurrence.holder.DeferredResultHolder;
import com.syun.springbootconcurrence.queue.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @description: spring boot 并发测试
 * @program: springboot-concurrence
 * @author: syun
 * @create: 2018-10-18 15:22
 */
@RestController
@Slf4j
public class AsyncController {


    private RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder().build();

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;


    @RequestMapping("/order")
    public String order() throws InterruptedException {
        log.info("主线程开始");
        Thread.sleep(1000);
        log.info("主线程返回");
        return "success";
    }

    @RequestMapping("/orderMockQueue")
    public DeferredResult orderQueue() throws InterruptedException {
        log.info("主线程开始");
        String orderNumber = randomStringGenerator.generate(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult result = new DeferredResult();
        deferredResultHolder.getMap().put(orderNumber, result);
        Thread.sleep(1000);
        log.info("主线程返回");
        return result;
    }




}
