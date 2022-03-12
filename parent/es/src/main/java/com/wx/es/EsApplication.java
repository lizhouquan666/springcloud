package com.wx.es;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class },scanBasePackages = {"com.wx.es"})
@EnableSwagger2
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@EnableElasticsearchRepositories
@MapperScan("com.wx.es.mapper")
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

}
