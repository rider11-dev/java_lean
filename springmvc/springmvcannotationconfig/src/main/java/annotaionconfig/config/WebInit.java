package annotaionconfig.config;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//初始化类，代替web.xml
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    private Logger logger = LoggerFactory.getLogger(WebInit.class);
    public WebInit() {
        logger.debug("===========WebInit===========");
    }
    // 设置 Spring 的配置类
    @Override
    @Nullable
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SpringConfig.class };
    }

    // 设置 Spring MVC 的配置类
    @Override
    @Nullable
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    // 为 DispatcherServlet 指定映射规则，相当于 web.xml 中配置的 url-pattern
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // 添加过滤器
    @Override
    @Nullable
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceResponseEncoding(true);
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[] { encodingFilter, hiddenHttpMethodFilter };
    }
}
