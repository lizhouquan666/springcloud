package com.wx.user;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class },scanBasePackages = {"com.wx.user","com.wx.auth"})
//@EnableSwagger2
//@EnableTransactionManagement
//@EnableAspectJAutoProxy
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
//@EnableElasticsearchRepositories
@MapperScan("com.wx.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
