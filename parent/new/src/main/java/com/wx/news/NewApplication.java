package com.wx.news;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class },scanBasePackages = {"com.wx.news"})
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@MapperScan("com.wx.news.mapper")
public class NewApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewApplication.class, args);
    }

}
