package annotaionconfig.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import annotaionconfig.entity.User;

//代替spring的配置文件
@Configuration
public class SpringConfig {
    private Logger logger = LoggerFactory.getLogger(WebConfig.class);
    public SpringConfig() {
        logger.debug("===========SpringConfig===========");
    }
    @Bean
    public User User() {
        User user = new User();
        user.setUserName("小明");
        user.setBirth(new Date());
        user.setHeight(175.0);
        return user;
    }
}
