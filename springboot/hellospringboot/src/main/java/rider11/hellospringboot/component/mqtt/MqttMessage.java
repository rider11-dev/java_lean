package rider11.hellospringboot.component.mqtt;

import lombok.Data;

@Data
public class MqttMessage<T> {
    private T data;
    public long time;
    public MqttMessage() {}
    public MqttMessage(T data) {
        this.data = data;
        time = System.currentTimeMillis();
    }
}
