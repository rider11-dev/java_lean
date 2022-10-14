package rider11.hellospringboot.component.mqtt;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MqttClientManager {
    private MqttProperties props;

    private MqttClient client;
    private Object connectLock = new Object();
    private Object publishLock = new Object();

    public MqttClientManager(MqttProperties props) {
        this.props = props;
    }

    public MqttProperties getProperties() {
        return this.props;
    }

    public MqttClient getClient() throws MqttException {
        return client;
    }

    public void connect() throws MqttException {
        synchronized (connectLock) {
            if (client == null) {
                client = new MqttClient(props.getHost(), props.getClientId(), new MemoryPersistence());
                client.setCallback(new MqttCallbackImpl(this));
            }
            if (!client.isConnected()) {
                client.connect(props.buildOptions());
            }
            subscribe("hellospring/mqtt", 1);
        }
        log.debug("mqtt连接:{}", client.isConnected());
    }

    public void publish(String data, String topic) throws UnsupportedEncodingException, MqttException {
        publish(data, topic, 1, true);// 默认qos为1,持久化
    }

    /**
     * 
     * @param data
     * @param topic
     * @param qos
     * @param retained 是否持久化
     * @throws UnsupportedEncodingException
     * @throws MqttException
     */
    private void publish(String data, String topic, int qos, boolean retained)
            throws UnsupportedEncodingException, MqttException {
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes("utf-8"));
        message.setQos(qos);
        message.setRetained(retained);
        MqttClient client = getClient();
        if (client == null) {
            throw new RuntimeException("获取MqttClient失败");
        }
        MqttTopic mqttTopic = client.getTopic(topic);
        if (mqttTopic == null) {
            log.error("topic不存在:{}", topic);
        }
        MqttDeliveryToken token;
        // 注意：这里一定要同步，否则，在多线程publish的情况下，线程会发生死锁
        synchronized (publishLock) {
            token = mqttTopic.publish(message);// 发送到执行队列中，等待执行线程执行，将消息发送到消息中间件
            token.waitForCompletion(1000L);
        }
    }

    /**
     * 订阅
     * 
     * @param topic
     * @param qos
     * @throws MqttException
     */
    public void subscribe(String topic, int qos) throws MqttException {
        MqttClient client = getClient();
        if (client != null) {
            client.subscribe(topic);
        }
    }

    /**
     * 取消订阅
     * 
     * @param topic
     * @throws MqttException
     */
    public void unSubscribe(String topic) throws MqttException {
        MqttClient client = getClient();
        if (client != null) {
            client.unsubscribe(topic);
        }
    }
}
