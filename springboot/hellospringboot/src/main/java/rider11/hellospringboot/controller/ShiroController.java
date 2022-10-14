package rider11.hellospringboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rider11.hellospringboot.entity.ShiroAccount;

@Controller
@RequestMapping("/shiro")
public class ShiroController {
    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return "shiro/" + url;
    }

    @PostMapping("login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            ShiroAccount account = (ShiroAccount) subject.getPrincipal();
            subject.getSession().setAttribute("account", account);
            return "shiro/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "shiro/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "shiro/login";
        }
    }

    @GetMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "shiro/login";
    }

    @RequestMapping("unauth")
    @ResponseBody
    public String unauth() {
        return "未授权没有访问权限";
    }
}
