package rider11.hellospringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String HelloWorld() {
        logger.debug("调用了hello接口");
        return "你好,world!";
    }

    @RequestMapping("/err")
    public void error() {
        logger.debug("调用err接口");
        int a = 10 / 0;
    }
}
