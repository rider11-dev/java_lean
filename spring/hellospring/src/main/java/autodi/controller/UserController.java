package autodi.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import autodi.service.UserService;

@Controller("userController")
public class UserController {
    @Resource
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void doStr() {
        userService.out();
        System.out.println("你好，这是:" + UserController.class.getName());
    }
}
