package rider11.hellospringboot.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import rider11.hellospringboot.component.LoginIntercepter;

//实现WebMvcConfigurer扩展SpringMVC
// @EnableWebMvc //如果注释掉，则只扩展，不接管SpringMVC
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/main").setViewName("main");

        registry.addViewController("/swagger").setViewName("redirect:/swagger-ui/index.html");
    }

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter())
                // .addPathPatterns("/**")// 拦截所有请求，包括静态资源文件
                .addPathPatterns("/main.html")
                .excludePathPatterns("/", "/login", "/index.html")//
        ;
    }

    // 启用swagger-bootstrap-ui
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // registry.addResourceHandler("doc.html")
    // .addResourceLocations("classpath:/META-INF/resources/");
    // }
    // 手动整合druid
    // /**
    // * 启用druid数据源
    // *
    // * @return
    // * @throws SQLException
    // */
    // @ConfigurationProperties("spring.datasource")
    // @Bean
    // public DataSource dataSource() throws SQLException {
    // DruidDataSource ds = new DruidDataSource();
    // // 同时开启 sql 监控(stat) 和防火墙(wall)，中间用逗号隔开。
    // // 开启防火墙能够防御 SQL 注入攻击
    // ds.setFilters("stat,wall");
    // return ds;
    // }

    // /**
    // * 开启 Druid 数据源内置监控页面
    // *
    // * @return
    // */
    // @Bean
    // public ServletRegistrationBean statViewServlet() {
    // StatViewServlet servlet = new StatViewServlet();
    // ServletRegistrationBean<Servlet> reg = new
    // ServletRegistrationBean<Servlet>(servlet, "/druid/*");
    // reg.addInitParameter("loginUsername", "admin");
    // reg.addInitParameter("loginPassword", "123456");
    // return reg;
    // }

    // /**
    // * 向容器中添加 WebStatFilter
    // * 开启内置监控中的 Web-jdbc 关联监控的数据
    // *
    // * @return
    // */
    // @Bean
    // public FilterRegistrationBean druidWebStatFilter() {
    // WebStatFilter webStatFilter = new WebStatFilter();
    // FilterRegistrationBean filterRegistrationBean = new
    // FilterRegistrationBean(webStatFilter);
    // // 监控所有的访问
    // filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
    // // 监控访问不包括以下路径
    // filterRegistrationBean.addInitParameter("exclusions",
    // "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    // return filterRegistrationBean;
    // }
}
