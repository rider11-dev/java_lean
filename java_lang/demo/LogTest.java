import java.util.logging.Logger;

public class LogTest {
    private static Logger log = Logger.getLogger(LogTest.class.getName());

    public static void main(String[] args) {
        log.finest("finest");
        log.finer("finer");
        log.config("config");
        log.info("info");
        log.warning("warning");
        log.severe("severe");
    }
}
