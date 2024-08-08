package com.mingyu.consumer.feign.service;

import com.mingyu.consumer.feign.service.fallback.FeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mingyu-provider",fallback = FeignServiceFallback.class)
public interface FeignService {
    @GetMapping(value = "/test/{message}")
    String test(@PathVariable("message")String message);

}
