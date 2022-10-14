package aspectj.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component("myAspect4")
public class MyAspect4 {
    @Pointcut("execution(* aspectj.service.*.exception(..))")
    public void pointcut4() {
    }

    // 异常
    // throwing的值e要和afterThrowing3方法参数名一致
    @Order(1)
    @AfterThrowing(value = "pointcut4()", throwing = "e")
    public void afterThrowing4(Exception e) {
        System.out.println("====AfterThrowing4:Exception," + e.getMessage());
    }
}
