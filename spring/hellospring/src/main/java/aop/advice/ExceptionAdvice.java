package aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Component("exceptionAdvice")
public class ExceptionAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println(
                "方法[" + method.getDeclaringClass().getName() + ",method.getName() " + "]执行异常:" + ex.getMessage());
    }
}
