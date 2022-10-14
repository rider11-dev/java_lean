package hello.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.model.User;

@Controller
@RequestMapping("/param")
public class ParamController {
    private static final Logger logger = LoggerFactory.getLogger(ParamController.class);

    /**
     * 通过 HttpServletRequest 获取请求参数
     * 
     * @param request
     * @return
     */
    @RequestMapping("getRequestParam")
    public String requestParameter(HttpServletRequest request) {
        String name = request.getParameter("name");
        logger.info("name:" + name);
        return "param/index";
    }

    /**
     * 通过形参获取请求参数
     * 
     * @param name
     * @apiNote http://host:8889/hellospringmvc/param/getFormalParam?name=张鹏飞&age=32&courses=语文&courses=数学&hobits=篮球&hobits=电影
     * @return
     */
    @RequestMapping("getFormalParam")
    public String formalParameter(String name, Integer age, String[] courses, String hobits) {
        logger.info("name:" + name);
        logger.info("age:" + age);
        logger.info("courses:" + Arrays.toString(courses));// courses:[语文, 数学]
        logger.info("hobits:" + hobits);// hobits:篮球,电影
        return "param/index";
    }

    /**
     * 通过@RequestParam注解获取
     * 
     * @param username
     * @param password
     * @apiNote http://host:8889/hellospringmvc/param/getAnnotation?name=张鹏飞&pass=123123
     * @return
     */
    @RequestMapping("getAnnotation")
    public String annotation(@RequestParam("name") String username, @RequestParam("pass") String password) {
        logger.info("username:" + username);
        logger.info("password:" + password);
        return "param/index";
    }

    /**
     * 通过实体类获取请求参数
     * 
     * @param user
     * @apiNote getEntity?UserId=rider11&UserName=十一&Age=32
     * @return
     */
    @RequestMapping("getEntity")
    public String entity(User user) {
        logger.info("user:" + user.toString());
        return "param/index";
    }
}
