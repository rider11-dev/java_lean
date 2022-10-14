package rider11.hellospringboot.controller;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rider11.hellospringboot.component.mqtt.MqttClientManager;
import rider11.hellospringboot.component.mqtt.MqttMessage;
import rider11.hellospringboot.entity.User;

@RestController
@RequestMapping("mqtt")
public class MqttController {
    private MqttClientManager manager;
    private ObjectMapper objectMapper;

    public MqttController(MqttClientManager manager, ObjectMapper objectMapper) {
        this.manager = manager;
        this.objectMapper = objectMapper;
    }

    @GetMapping("publish")
    public String publish(String topic, User user)
            throws JsonProcessingException, UnsupportedEncodingException, MqttException {
        MqttMessage<User> msg = new MqttMessage<User>(user);
        String data = this.objectMapper.writeValueAsString(msg);
        this.manager.publish(data, topic);
        return "ok";
    }
}
