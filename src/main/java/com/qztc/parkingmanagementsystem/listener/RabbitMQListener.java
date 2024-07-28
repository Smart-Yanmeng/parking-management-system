package com.qztc.parkingmanagementsystem.listener;

import com.qztc.parkingmanagementsystem.config.OrderMqConfig;
import com.qztc.parkingmanagementsystem.domain.emuns.OrderStatusemuns;
import com.qztc.parkingmanagementsystem.domain.po.BOrder;
import com.qztc.parkingmanagementsystem.mapper.IOrderMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author congyijiu
 * @create 2024-07-28-22:43
 */

@Component
@Slf4j
public class RabbitMQListener {



    @Autowired
    private IOrderMapper iOrderMapper;

    @RabbitListener(queues = OrderMqConfig.DEAD_ORDER_QUEUE, ackMode = "MANUAL", concurrency = "3")
    public void receiveMessageDead(String message,
                                   Channel channel,
                                   @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag
    ) throws IOException {
        try {

            Long orderId = Long.valueOf(message);
            //根据订单id查询订单信息
            BOrder bOrder = iOrderMapper.findById(orderId);

            //如果订单状态不是已支付，则取消订单
            if (!bOrder.getStatus().equals(OrderStatusemuns.PAID.getStatus()) &&
                    !bOrder.getStatus().equals(OrderStatusemuns.COMPLETED.getStatus())) {
                bOrder.setStatus(OrderStatusemuns.CANCEL.getStatus());
                iOrderMapper.updateById(bOrder);
            }
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            channel.basicNack(deliveryTag, false, true);
            log.error("消息处理失败", e);
            throw new RuntimeException(e);
        } finally {
            log.info("消息处理完成");
        }
    }
}
