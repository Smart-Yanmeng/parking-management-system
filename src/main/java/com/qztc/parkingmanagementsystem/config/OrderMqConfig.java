package com.qztc.parkingmanagementsystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
public class OrderMqConfig {
    //普通交换机名称
    public static final String ORDER_CREATE_EXCHANGE="order_create_exchange";
    //死信交换机的名称
    public static final String ORDER_DEAD_LETTER_EXCHANGE="order_dead_exchange";
    //普通队列的名称
    public static final String QUEUE_ORDER="order_queue";
    //死信队列名称
    public static final String DEAD_ORDER_QUEUE="order_dead_queue";


    @Bean("orderCreateExchange")
    public DirectExchange orderCreateExchange(){
        return new DirectExchange(ORDER_CREATE_EXCHANGE);
    }

    @Bean("orderDeadExchange")
    public DirectExchange orderDeadExchange(){
        return new DirectExchange(ORDER_DEAD_LETTER_EXCHANGE);
    }

    //声明队列
    @Bean("orderQueue")
    public Queue orderQueue(){
        Map<String,Object> arguments=new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",ORDER_DEAD_LETTER_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","YD");
        //设置过期时间  单位是ms，这里可以根据自己业务时间设置为超时的时间，这里是10s
        arguments.put("x-message-ttl",10000);
        return QueueBuilder.durable(QUEUE_ORDER)
                .withArguments(arguments).build();
    }

    //死信队列
    @Bean("orderDeadQueue")
    public Queue orderDeadQueue(){
        return QueueBuilder.durable(DEAD_ORDER_QUEUE).build();
    }

    //绑定
    // @Qualifier是对应上面bean的别名
    @Bean
    public Binding orderQueueBingOrderCreateExchange(@Qualifier("orderQueue")Queue queueA,
                                                     @Qualifier("orderCreateExchange")DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    @Bean
    public Binding orderDeadQueueBingOrderDeadExchange(@Qualifier("orderDeadQueue")Queue queueD,
                                                       @Qualifier("orderDeadExchange")DirectExchange xExchange){
        return BindingBuilder.bind(queueD).to(xExchange).with("YD");
    }


    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    // 3.1.ack，消息成功
                    log.debug("通知支付结果消息发送成功, ID:{}", correlationData != null ? correlationData.getId() : null);
                } else {
                    // 3.2.nack，消息失败
                    log.error("通知支付结果消息发送失败, ID:{}, 原因{}", correlationData != null ? correlationData.getId() : null, cause);
                }
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                log.error("消息被退回: {}, 原因: {}", returnedMessage.getMessage(), returnedMessage.getReplyText());
            }

        });

        return rabbitTemplate;
    }

}
