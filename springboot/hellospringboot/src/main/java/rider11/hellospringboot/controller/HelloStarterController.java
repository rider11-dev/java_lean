package rider11.hellospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rider11.hellostarterspringbootstarter.HelloService;
@RequestMapping("/starter")
@RestController
public class HelloStarterController {
    private HelloService helloService;

    public HelloStarterController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.helloService.say();
    }
}
