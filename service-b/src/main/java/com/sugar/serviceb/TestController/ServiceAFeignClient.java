package com.sugar.serviceb.TestController;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("SERVICE-OBJECT-A")
public interface ServiceAFeignClient {
    @RequestMapping("/hello")
    public String hello();
}
