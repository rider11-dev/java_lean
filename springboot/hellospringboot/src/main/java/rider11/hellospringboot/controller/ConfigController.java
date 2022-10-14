package rider11.hellospringboot.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import rider11.hellospringboot.bean.HelloApollo;
import rider11.hellospringboot.entity.User;

@RestController
@RequestMapping("/config")
@Api(value = "配置api", tags = { "配置接口" })
public class ConfigController {
    private Logger logger = LoggerFactory.getLogger(ConfigController.class);
    @Value(value = "${self.user.name}")
    private String name;
    private User user;
    private Environment env;
    @Value(value = "${server.port}")
    private String apolloServerPort;
    @Value(value = "${helloapollo.name}")
    private String apolloHelloName;
    private HelloApollo helloApollo;

    public ConfigController(User user, Environment env, HelloApollo helloApollo) {
        this.user = user;
        this.env = env;
        this.helloApollo = helloApollo;
    }

    @ApiOperation("获取用户姓名，测试@Value注解")
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName() {
        logger.debug("调用/name");
        return this.name;
    }

    @RequestMapping(value = "/hobits", method = RequestMethod.GET)
    public List<String> getHobits() {
        logger.debug("调用/hobits");
        return this.user.getHobits();
    }

    @ApiOperation("获取用户信息配置")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
        logger.debug("调用/user");
        return this.user;
    }

    @ApiOperation("获取当前时间")
    @RequestMapping(value = "/now", method = RequestMethod.GET)
    public Date getNow() {
        logger.debug("调用/now");
        return new Date();
    }

    @ApiOperation("获取所有配置")
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Map<String, String> getConfigs(@ApiParam("配置过滤前缀") @RequestParam(required = false) String prefix) {
        TreeMap<String, String> configs = new TreeMap<>();
        Boolean hasPrefix = prefix != null && !prefix.isEmpty();
        for (PropertySource<?> propertySource : ((AbstractEnvironment) env).getPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource) {
                for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                    if (name != null && (!hasPrefix || name.startsWith(prefix))) {
                        configs.put(name, env.getProperty(name));
                    }
                }
            }
        }
        return configs;
    }

    @ApiOperation("获取apollo配置:server.port")
    @RequestMapping(value = "/apollo_server_port", method = RequestMethod.GET)
    public String getApolloServerPort() {
        return this.apolloServerPort;
    }

    @ApiOperation("获取apollo配置:helloapollo.name")
    @RequestMapping(value = "/apollo_helloapollo_name", method = RequestMethod.GET)
    public String getApolloHelloName() {
        return this.apolloHelloName;
    }

    @ApiOperation("获取apollo配置:helloapollo")
    @RequestMapping(value = "/apollo_helloapollo", method = RequestMethod.GET)
    public HelloApollo getApolloHello() {
        return this.helloApollo;
    }
}
