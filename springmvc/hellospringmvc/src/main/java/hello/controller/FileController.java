package hello.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hello.model.Student;

@Controller
@RequestMapping("/file")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> upload(MultipartFile photo, HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        if (photo == null) {
            setError(result, "请选择要上传的文件！");
            return result;
        }
        if (photo.getSize() > 1024 * 1024 * 10) {
            setError(result, "文件大小不能超过10M！");
            return result;
        }
        // 获取文件后缀
        String fileName = photo.getOriginalFilename();
        logger.info("正在上传的图片为：" + fileName);
        String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
        if (!"jpg,jpeg,git,png".toUpperCase().contains(suffix.toUpperCase())) {
            setError(result, "请选择jpg,jpeg,git,png格式的图片！");
            return result;
        }
        String realPath = request.getServletContext().getRealPath("/upload/");
        logger.info("物理路径:" + realPath);
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String newFileName = UUID.randomUUID() + fileName;
        try {
            photo.transferTo(new File(folder, newFileName));
        } catch (Exception e) {
            setError(result, "保存文件异常");
            logger.error("保存文件异常", e);
            return result;
        }

        result.put("type", "success");
        result.put("msg", "上传图片成功！");
        result.put("filepath", "upload/");
        // result.put("filepath", "/upload/");
        result.put("filename", newFileName);

        return result;
    }

    private void setError(Map<String, String> result, String error) {
        result.put("type", "error");
        result.put("msg", error);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String save(Student student, Model model) {
        String fileNameStr = student.getFileNameStr();
        String[] splits = fileNameStr.split(",");
        List<String> list = new ArrayList<String>();
        for (String fileName : splits) {
            list.add(fileName);
        }
        student.setPath(list);
        model.addAttribute("student", student);

        return "file/success";
    }

    @RequestMapping("download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, String fileName) throws IOException {
        String folder = request.getServletContext().getRealPath("/upload/");
        File file = new File(folder, fileName);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", toUTF8String(fileName));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    // 文件名字符编码转换
    private String toUTF8String(String str) {
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            // 取出字符中的每个字符
            char c = str.charAt(i);
            // Unicode码值为0~255时，不做处理
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else { // 转换 UTF-8 编码
                byte b[];
                try {
                    b = Character.toString(c).getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    b = null;
                }
                // 转换为%HH的字符串形式
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k &= 255;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
