package rider11.hellospringboot.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rider11.hellospringboot.config.RabbitmqConfig;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {
    private RabbitTemplate rabbitTemplate;

    public RabbitmqController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("publish")
    public String publish(String message) {
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.Exchange_Hello, RabbitmqConfig.RoutingKey_Hello,
                message);
        return "ok";
    }
}
