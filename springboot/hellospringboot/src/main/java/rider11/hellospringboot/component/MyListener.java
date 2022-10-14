package rider11.hellospringboot.component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.debug("MyListener监听到ServletContext初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.debug("MyListener监听到ServletContext销毁");
    }
}
