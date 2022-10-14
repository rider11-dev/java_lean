package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String sayHello() {
        // 视图名，视图为：视图前缀+index+视图后缀，即 /WEB-INF/template/index.html
        return "index";
    }

    @RequestMapping("/login")
    public String welcome() {
        // 视图名，视图为：视图前缀+login+视图后缀，即 /WEB-INF/template/login.html
        return "login";
    }

    @RequestMapping("/register")
    public String success() {
        // 视图名，视图为：视图前缀+register+视图后缀，即 /WEB-INF/template/register.html
        return "register";
    }
}
