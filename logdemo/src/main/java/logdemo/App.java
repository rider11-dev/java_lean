package logdemo;

// log4j
import org.apache.log4j.Logger;

//log4j2
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
    //log4j
    private static Logger logger = Logger.getLogger(App.class);
    //log4j2
    // private static Logger logger = LogManager.getLogger(App.class);    

    public static void main(String[] args) {
        System.out.println("Hello World!");
        logger.debug("测试debug日志");
        logger.info("测试info");
        logger.warn("测试warn");
        logger.error("测试error");
        logger.fatal("测试fatal");
    }
}
