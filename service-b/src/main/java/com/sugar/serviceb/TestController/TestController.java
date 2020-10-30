package com.sugar.serviceb.TestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ServiceAFeignClient serviceAFeignClient;
    @RequestMapping("/call")
    public String call(){
        String result = serviceAFeignClient.hello();
        return "b to a 访问------ " + result;
    }
}
