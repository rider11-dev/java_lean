package rider11.hellospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RabbitmqConfig {
    public static final String Queue_Hello = "hellospringboot.queue";
    public static final String Exchange_Hello = "hellospringboot.event";
    public static final String RoutingKey_Hello = "hellospringboot";

    // 声明交换机
    @Bean(Exchange_Hello)
    public Exchange getRabbitmqEventExchange() {
        return ExchangeBuilder.topicExchange(Exchange_Hello).durable(true).build();
    }

    // 声明队列
    @Bean(Queue_Hello)
    public Queue getClientsConnectionQueue() {
        return new Queue(Queue_Hello);
    }

    // 声明绑定
    @Bean
    public Binding getClientsConnectionQueueBinding(@Qualifier(Queue_Hello) Queue queue,
            @Qualifier(Exchange_Hello) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RoutingKey_Hello).noargs();
    }
}
