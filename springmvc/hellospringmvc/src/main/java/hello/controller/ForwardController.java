package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/forward")
public class ForwardController {
    @RequestMapping("/test1")
    public String testDispatcher1(Model model) {
        model.addAttribute("requestScope", "控制器返回值：转发！");
        // 通过返回值将请求转发的 /userPage 上
        return "forward:/user";
    }

    @RequestMapping("test2")
    public ModelAndView testDispatcher2() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:/user");
        mv.addObject("requestScope", "ModelAndView：转发！");
        return mv;
    }
}
