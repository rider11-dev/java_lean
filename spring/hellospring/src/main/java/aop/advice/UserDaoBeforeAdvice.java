package aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

//前置增强
@Component("beforeAdvice")
public class UserDaoBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("正在执行前置增强操作.......");
    }
}
