package com.mingyu.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String appName;

    /**
     * 分布式中服务消费为前端提供接口调用功能，根据调用的要求将数据拆分给各个服务提供者，以实现前端需求，简单来讲，服务提供者是基础配件厂家，服务消费者是组装公司，前端是客户，客户下单，服务消费者根据用户需求，
     * 向服务提供者选择不同配件，最终组装成客户需要，返回给客户。
     * @return
     */
    @GetMapping(value = "/test/app/name")
    public String test(){
        ServiceInstance serviceInstance= loadBalancerClient.choose("mingyu-provider");
        String url = String.format("http://%s:%s/test/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);

        return restTemplate.getForObject(url,String.class);
    }
}
