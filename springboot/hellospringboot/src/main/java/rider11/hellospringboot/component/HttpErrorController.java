package rider11.hellospringboot.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HttpErrorController extends AbstractErrorController {
    private final static String ERROR_PATH = "/error";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public HttpErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    public static String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    @ResponseBody
    public Map error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            ErrorAttributeOptions options = ErrorAttributeOptions.of(
                    ErrorAttributeOptions.Include.BINDING_ERRORS,
                    ErrorAttributeOptions.Include.EXCEPTION,
                    ErrorAttributeOptions.Include.MESSAGE,
                    ErrorAttributeOptions.Include.STACK_TRACE);
            Map<String, Object> attributes = getErrorAttributes(request, options);
            String path = attributes.get("path").toString();
            Integer status = (Integer) attributes.get("status");
            Object val = attributes.get("trace");
            String trace = val == null ? "" : val.toString();
            log.error("status: {},path: {},trace: {}", status, path, trace);
            Date timestamp = (Date) attributes.get("timestamp");
            String error = attributes.get("error").toString();
            map.put("code", status);
            map.put("message", error);
            map.put("timestamp", sdf.format(timestamp));
            return map;
        } catch (Exception ex) {
            log.error("异常处理错误", ex);
            map.put("code", 500);
            map.put("message", "异常处理错误");
            map.put("timestamp", sdf.format(new Date()));
            return map;
        }
    }
}
