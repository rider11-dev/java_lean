package aspectj.aspect;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component("myAspect2")
public class MyAspect2 {
    public void before2(JoinPoint joinPoint) {
        System.out.println("=======MyAspect2 Before " + joinPoint.getSignature().getName());
    }
}
