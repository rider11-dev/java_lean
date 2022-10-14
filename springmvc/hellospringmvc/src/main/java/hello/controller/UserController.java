package hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.model.User;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/converter/user", method = RequestMethod.POST)
    public String login(User user, Model model) {
        logger.debug("用户信息：" + user.toString());
        model.addAttribute("user", user);

        return "converter/success";
    }
}
