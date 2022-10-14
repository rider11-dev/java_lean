package rider11.hellospringboot.component;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.config.RabbitmqConfig;

@Component
@Slf4j
public class RabbitmqHandler {
    @RabbitListener(queues = { RabbitmqConfig.Queue_Hello })
    public void receiveHello(Message message, Channel channel) throws UnsupportedEncodingException {
        log.debug("receiveHello==>body:{}", getBody(message));
        basicAck(message, channel);
    }

    @RabbitListener(queues = { "clients.connection" })
    public void receiveConnections(Message message, Channel channel) throws UnsupportedEncodingException {
        log.debug("receiveConnections==>body:{}", getBody(message));
        basicAck(message, channel);
    }

    private void basicAck(Message message, Channel channel) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception ex) {
            log.error("消息ack失败:{}", ex.getMessage());
        }
    }

    private String getBody(Message message) throws UnsupportedEncodingException {
        return new String(message.getBody(), "utf-8");
    }
}
