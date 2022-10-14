package annotaionconfig.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import annotaionconfig.entity.User;

@Controller
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);
    private User user;

    public TestController(User user) {
        this.user = user;
    }

    // 测试spring配置是否生效
    @ResponseBody
    @GetMapping("/test")
    public User get() {
        return user;
    }

    // 测试异常映射
    @GetMapping("/test_exception")
    public User testException() {
        int a = 10 / 0;
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request, Model model) {
        List<String> newFileNameList = new ArrayList<>();
        List<MultipartFile> photos = user.getPhotos();
        for (MultipartFile photo : photos) {
            String realPath = request.getServletContext().getRealPath("/upload/");
            logger.debug("物理路径:" + realPath);
            File fileDir = new File(realPath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            String filename = photo.getOriginalFilename();
            logger.debug("正在上传的图片为：" + filename);
            String newFileName = UUID.randomUUID() + filename;
            try {
                // 将文件保存指定目录
                photo.transferTo(new File(realPath + newFileName));
                newFileNameList.add(newFileName);
            } catch (Exception e) {
                logger.error("保存文件异常", e);
            }
        }
        logger.debug(user.toString());
        model.addAttribute("type", "success");
        model.addAttribute("user", user);
        model.addAttribute("filePath", "/upload/");
        model.addAttribute("fileNameList", newFileNameList);
        return "success";
    }
}
