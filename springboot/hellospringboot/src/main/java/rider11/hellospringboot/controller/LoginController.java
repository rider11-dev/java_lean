package rider11.hellospringboot.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.bean.MyUser;
import rider11.hellospringboot.entity.User;
import rider11.hellospringboot.service.UserService;

@Slf4j
@Controller
public class LoginController {
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/login")
    public String doLogin(User user, Map<String, Object> map, HttpSession session) {
        if (user != null && StringUtils.hasText(user.getName()) && "123456".equals(user.getPassword())) {
            session.setAttribute("loginUser", user);
            log.debug("登陆成功，用户名：" + user.getName());
            // 防止重复提交使用重定向
            return "redirect:/main";
        } else {
            map.put("msg", "用户名或密码错误");
            log.error("登陆失败");
            return "login";
        }
    }

    @RequestMapping("/user/login1")
    public String doLogin1(MyUser user, Map<String, Object> map, HttpSession session) {
        MyUser u = userService.getByUserNameAndPassword(user);
        if (u != null) {
            session.setAttribute("loginUser", u);
            log.info("登录成功：" + user.getUserName());
            return "redirect:/main";
        }
        map.put("msg", "用户名或密码错误");
        return "login";
    }
}
