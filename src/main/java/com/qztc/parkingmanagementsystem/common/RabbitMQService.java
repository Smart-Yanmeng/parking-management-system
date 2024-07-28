package com.qztc.parkingmanagementsystem.common;

import com.qztc.parkingmanagementsystem.config.OrderMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author congyijiu
 * @create 2024-07-28-21:55
 */

@Component
@Slf4j
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrderMessage(String message) {
        rabbitTemplate.convertAndSend(OrderMqConfig.ORDER_CREATE_EXCHANGE, OrderMqConfig.ORDER_CREATE_ROUTING_KEY, message);
    }


}
