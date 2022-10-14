package annotaionconfig.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public String exceptionAdvice(Exception ex, Model model) {
        System.out.println("全局异常处理:" + ex.toString());
        model.addAttribute("ex", ex);
        return "error_global";
    }
}
