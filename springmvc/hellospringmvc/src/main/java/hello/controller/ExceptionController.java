package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.dao.UserDao;
import hello.exception.UserNotExistsException;
import hello.model.User;

@Controller
@RequestMapping("/exception")
public class ExceptionController {
    private UserDao userDao;

    public ExceptionController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "success";
    }

    @RequestMapping(value = "/default_handler_exception_resolver", method = RequestMethod.POST)
    public String testDefaultHandlerExceptionResolver() {
        return "success";
    }

    @RequestMapping("/response_status_exception_resolver")
    public String testResponseStatusExceptionResolver(String userName, Model model) {
        User user = this.userDao.getUserByName(userName);
        if (user == null) {
            throw new UserNotExistsException();
        }
        return "success";
    }

    @RequestMapping("/exception_handler_exception_resolver")
    public String testExceptionHandlerExceptionResolver(Integer val) {
        System.out.println(10 / val);
        return "success";
    }

    // 定义一个异常处理方法
    @ExceptionHandler(ArithmeticException.class)
    public String handleException(ArithmeticException exception, Model model) {
        model.addAttribute("ex", exception);
        return "error";
    }

    @RequestMapping("/global_exception_handler")
    public String testGlobalExceptionHandler(Boolean flag) {
        if (flag) {
            throw new IllegalArgumentException("测试全局异常");
        }
        return "success";
    }
}
