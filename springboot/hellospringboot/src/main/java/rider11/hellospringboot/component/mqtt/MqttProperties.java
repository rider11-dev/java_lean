package rider11.hellospringboot.component.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {
    private String host;
    private String username;
    private String password;
    private Integer qos;
    private String clientId;
    private Integer timeout;
    private Integer keepalive;

    public MqttConnectOptions buildOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(timeout);
        options.setKeepAliveInterval(keepalive);
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        return options;
    }
}
