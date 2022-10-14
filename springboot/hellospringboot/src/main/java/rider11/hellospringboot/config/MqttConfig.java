package rider11.hellospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.component.mqtt.MqttClientManager;
import rider11.hellospringboot.component.mqtt.MqttProperties;

@Configuration
@Slf4j
public class MqttConfig {
    private MqttProperties props;

    public MqttConfig(MqttProperties props) {
        this.props = props;
    }

    @Bean
    public MqttClientManager getMqttClientManager() {
        MqttClientManager manager = new MqttClientManager(this.props);
        try {
            manager.connect();
        } catch (Exception e) {
            log.error("mqtt连接失败:{}", e.toString());
        }
        return manager;
    }
}
