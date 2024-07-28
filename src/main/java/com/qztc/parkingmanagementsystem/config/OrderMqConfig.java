package com.qztc.parkingmanagementsystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
    //普通队列RoutingKey
    public static final String ORDER_CREATE_ROUTING_KEY="XA";
    //死信队列RoutingKey
    public static final String DEAD_ROUTING_KEY="YD";


    //声明队列
    @Bean
    public Queue orderQueue(){
        return QueueBuilder.durable(QUEUE_ORDER)
                .withArgument("x-dead-letter-exchange", ORDER_DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_ROUTING_KEY)
                .withArgument("x-message-ttl", 10000)  // 队列中的消息未被消费则10秒后过期
                .build();
    }

    //声明交换机
    @Bean
    public DirectExchange orderCreateExchange(){
        return new DirectExchange(ORDER_CREATE_EXCHANGE,true, false);
    }

    //队列绑定交换机
    @Bean
    public Binding orderQueueBingOrderCreateExchange(Queue orderQueue,
                                                     DirectExchange orderCreateExchange){
        return BindingBuilder.bind(orderQueue).to(orderCreateExchange).with(ORDER_CREATE_ROUTING_KEY);
    }


    //死信队列
    @Bean
    public Queue orderDeadQueue(){
        return new Queue(DEAD_ORDER_QUEUE, true);
    }

    //死信交换机
    @Bean
    public DirectExchange orderDeadExchange(){
        return new DirectExchange(ORDER_DEAD_LETTER_EXCHANGE,true, false);
    }


    //死信队列绑定死信交换机
    @Bean
    public Binding orderDeadQueueBingOrderDeadExchange(Queue orderDeadQueue,
                                                       DirectExchange orderDeadExchange){
        return BindingBuilder.bind(orderDeadQueue).to(orderDeadExchange).with(DEAD_ROUTING_KEY);
    }

//
//    @Bean
//    public CachingConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
//        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
//        connectionFactory.setPublisherReturns(true);
//        return connectionFactory;
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                if (ack) {
//                    // 3.1.ack，消息成功
//                    log.debug("通知支付结果消息发送成功, ID:{}", correlationData != null ? correlationData.getId() : null);
//                } else {
//                    // 3.2.nack，消息失败
//                    log.error("通知支付结果消息发送失败, ID:{}, 原因{}", correlationData != null ? correlationData.getId() : null, cause);
//                }
//            }
//        });
//
//        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//            @Override
//            public void returnedMessage(ReturnedMessage returnedMessage) {
//                log.error("消息被退回: {}, 原因: {}", returnedMessage.getMessage(), returnedMessage.getReplyText());
//            }
//
//        });
//
//        return rabbitTemplate;
//    }

}
