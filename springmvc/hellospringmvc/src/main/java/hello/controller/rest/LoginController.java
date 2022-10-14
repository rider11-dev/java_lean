package hello.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.dao.UserDao;
import hello.model.User;

@Controller
public class LoginController {
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/rest/login")
    public String login(User user, HttpServletRequest request) {
        logger.info("登录参数:" + user.toString());
        User loginUser = this.userDao.getUserByName(user.getUserName());
        // logger.info("登录用户:" + (loginUser == null ? "null" : loginUser.toString()));
        if (loginUser != null && loginUser.getPassword().equals(user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            return "redirect:/rest/product/list";
        }
        request.setAttribute("msg", "账号或密码错误");
        return "rest/login";
    }
}
