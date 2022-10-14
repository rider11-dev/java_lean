package com.rider11.hellostarterspringbootstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnProperty(
    prefix = "hellostarter", 
    name = "enable", 
    havingValue = "true") //条件配置
public class HelloAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService() {
        log.debug("初始化HelloService...");
        return new HelloService();
    }
}
