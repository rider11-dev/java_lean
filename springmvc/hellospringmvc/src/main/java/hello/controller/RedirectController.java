package hello.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/redirect")
public class RedirectController {
    @RequestMapping("test1")
    public String test1(HttpSession session) {
        session.setAttribute("sessionScope", "控制器返回值：重定向！");
        return "redirect:/user";
    }

    @RequestMapping("test2")
    public ModelAndView test2(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/user");
        session.setAttribute("sessionScope", "ModelAndView：重定向！");
        return mv;
    }
}