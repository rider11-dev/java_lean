package annotaionconfig.config;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import annotaionconfig.converter.DateConverter;
import annotaionconfig.interceptors.MyInterceptor1;

//替代spring mvc的配置文件
@Configuration
// 扫描组件
@ComponentScan("annotaionconfig")
// 开启mvc注解驱动
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private Logger logger = LoggerFactory.getLogger(WebConfig.class);

    public WebConfig() {
        logger.debug("===========WebConfig===========");
    }

    // 使用默认的servlet处理静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        logger.debug("===========configureDefaultServletHandling===========");
        configurer.enable();
    }

    // 配置文件上传解析器
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        logger.debug("===========multipartResolver===========");
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxUploadSize(1024 * 1024 * 10);
        return resolver;
    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.debug("===========addInterceptors===========");
        MyInterceptor1 interceptor1 = new MyInterceptor1();
        registry.addInterceptor(interceptor1).addPathPatterns("/**").excludePathPatterns("/");
    }

    // 配置视图控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.debug("===========addViewControllers===========");
        registry.addViewController("/").setViewName("user");
    }

    // 配置异常映射
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        logger.debug("===========configureHandlerExceptionResolvers===========");
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties prop = new Properties();
        prop.setProperty("java.lang.Exception", "error");
        // 设置异常映射
        resolver.setExceptionMappings(prop);
        // 设置共享异常信息的键
        resolver.setExceptionAttribute("ex");
        resolvers.add(resolver);
    }

    // 配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        logger.debug("===========templateResolver===========");
        WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(ctx.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    // 生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        logger.debug("===========templateEngine===========");
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    // 生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine engine) {
        logger.debug("===========viewResolver===========");
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(engine);
        return resolver;
    }

    // 添加类型转换器和格式化器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        logger.debug("===========addFormatters===========");
        DateConverter converter = new DateConverter();
        registry.addConverter(converter);
    }
}
