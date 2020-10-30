package com.example.servicea.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.Main;

import java.math.BigDecimal;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;
    @Value("${name}")
    private String name;

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("线程池名称：" + Thread.currentThread().getName());

        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return "hello world 端口 : " + port;
    }

    String fallback(){
        return "服务器忙";
    }

    static Boolean  compare(BigDecimal a, BigDecimal b){
        if (a.compareTo(b) > 0){
            return true;
        }
        return false;
    }

    public static BigDecimal tranferData(String value1){
        if(value1.endsWith("T")){
            return new BigDecimal(value1.substring(0,value1.length()-1)).multiply(new BigDecimal("1000")).multiply(new BigDecimal("1000"));
        }
        if(value1.endsWith("G")){
            return new BigDecimal(value1.substring(0,value1.length()-1)).multiply(new BigDecimal("1000"));
        }
        return new BigDecimal(value1.substring(0,value1.length()-1));
    }

    @RequestMapping("/otherService")
    public String other(){
        return "我是其它服务!";
    }

    public static void main(String[] args) {
        String[] temp = {"10000000000000000M","20T","300000G"};
        for(int i=0;i<temp.length;i++){
            for(int j=i+1;j<temp.length;j++){
                if(compare(tranferData(temp[i]),tranferData(temp[j]))){
                    String dd = temp[i];
                    temp[i] = temp[j];
                    temp[j] = dd;
                }
                for(String aa : temp){
                    System.out.print(aa + "  " );

                }
                System.out.println();
            }
        }

        System.out.println(tranferData("100M").toString());
        System.out.println(tranferData("20T").toString());
        System.out.println(tranferData("30G").toString());

        for(String aa : temp){
            System.out.println(aa);
        }
    }

    @RequestMapping("/testConfig")
    String testConfig(){
        return name;
    }
}
