package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.model.Supermarket;

@Controller
public class FormatterMarketController {
    @RequestMapping(value = "/formatter/market", method = RequestMethod.POST)
    public String login(Supermarket market, Model model) {
        model.addAttribute("market", market);
        return "formatter/success";
    }
}
