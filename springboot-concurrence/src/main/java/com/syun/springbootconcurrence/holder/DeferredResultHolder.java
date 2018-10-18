package com.syun.springbootconcurrence.holder;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @program: springboot-concurrence
 * @author: syun
 * @create: 2018-10-18 15:41
 */
@Component
public class DeferredResultHolder {
    @Getter
    @Setter
    private Map<String, DeferredResult> map = new HashMap<>();
}
