package rider11.hellospringboot.config;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableApolloConfig({ "application", "helloapollo.yaml" })
public class ApolloConfig implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    // 监听配置改变事件
    @ApolloConfigChangeListener({ "application", "helloapollo.yaml" })
    private void onChange(ConfigChangeEvent changeEvent) {
        // for (String key : changeEvent.changedKeys()) {
        //     log.debug("配置变化:{}==>{}", key, changeEvent.getChange(key));
        // }
        refreshProperties(changeEvent);
    }

    private void refreshProperties(ConfigChangeEvent changeEvent) {

        // 触发@ConfigurationProperties注解的配置更新
        String keys = Arrays.toString(changeEvent.changedKeys().toArray());
        log.debug("刷新Properties配置开始:{}", keys);
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        log.debug("刷新Properties配置结束:{}", keys);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
