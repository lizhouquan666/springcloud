package com.wx.message.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public Object send(String message, String rk){
      rabbitTemplate.convertAndSend("userTopicExchange", rk, message);
      return rk + "  " + message;
    }
}
