package com.wx.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : fzf
 * @version V1.0
 * @Project: springcloud
 * @Package com.wx.admin
 * @Description:
 * @date Date : 2021年09月29日 16:31
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class AdminApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
    }
}
