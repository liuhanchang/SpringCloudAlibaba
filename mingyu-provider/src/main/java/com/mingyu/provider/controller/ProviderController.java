package com.mingyu.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @GetMapping(value = "/hi")
    public String sayHi(){
        return "hello "+ applicationContext.getEnvironment().getProperty("user.name");
    }

    @GetMapping(value = "/test/{message}")
    public String test(@PathVariable String message){
        return "当前服务收到消息："+message+",该服务由端口："+port+"提供。";
    }
}
