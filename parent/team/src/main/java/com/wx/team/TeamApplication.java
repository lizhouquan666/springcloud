package com.wx.team;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class },scanBasePackages = {"com.wx.team"})
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@MapperScan("com.wx.team.mapper")
public class TeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamApplication.class, args);
    }

}
