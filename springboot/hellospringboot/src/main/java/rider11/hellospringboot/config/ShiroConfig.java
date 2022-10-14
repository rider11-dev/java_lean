package rider11.hellospringboot.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import rider11.hellospringboot.component.ShiroUserPwdRealm;
import rider11.hellospringboot.service.ShiroAccountService;

@Configuration
public class ShiroConfig {

    /**
     * 自定义过滤器
     * 
     * @param accountService
     * @return
     */
    @Bean
    public ShiroUserPwdRealm userPwdRealm(ShiroAccountService accountService) {
        return new ShiroUserPwdRealm(accountService);
    }

    /**
     * 注册自定义过滤器
     * 
     * @param userPwdRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager manager(@Qualifier("userPwdRealm") ShiroUserPwdRealm userPwdRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userPwdRealm);
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        Map<String, String> map = new HashMap<>();
        map.put("/shiro/main", "authc");
        map.put("/shiro/manage", "perms[manage]");
        map.put("/shiro/admin", "roles[administrator]");
        factoryBean.setFilterChainDefinitionMap(map);
        // 设置登录页面
        factoryBean.setLoginUrl("/shiro/login");
        // 未授权页面
        factoryBean.setUnauthorizedUrl("/shiro/unauth");

        return factoryBean;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
