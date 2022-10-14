package rider11.hellospringboot.component.mqtt;

import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MqttCallbackImpl implements MqttCallback {

    private MqttClientManager manager;

    public MqttCallbackImpl(MqttClientManager manager) {
        this.manager = manager;
    }

    /**
     * 断开重连
     */
    @Override
    public void connectionLost(Throwable e) {
        log.warn("mqtt连接断开,5s后尝试重连:{}", e.getMessage());
        try {
            TimeUnit.SECONDS.sleep(5);
            manager.connect();
        } catch (Exception e1) {
            log.warn("mqtt连接重试异常:{}", e1.getMessage());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.debug("mqtt消息发送完成:{}", token.isComplete());

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String body = new String(message.getPayload(), CharsetUtil.UTF_8);
        log.debug("mqtt消息接收:{},{}", topic, body);
        // TODO:根据topic调用指定handler
    }

}
