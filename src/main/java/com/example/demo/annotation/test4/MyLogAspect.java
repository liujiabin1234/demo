package com.example.demo.annotation.test4;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyLogAspect.class);

    /**
     * 只要用到了MyLog这个注解的，就是目标类
     */
    @Pointcut("@annotation(com.example.demo.annotation.test4.MyLog)")
    private void MyValid() {
    }

    @Before("MyValid()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.debug("[" + signature.getName() + " : start.....]");

        MyLog myLog = signature.getMethod().getAnnotation(MyLog.class);
        logger.debug("【目标对象方法被调用时候产生的日志，记录到日志表中】：" + myLog.desc());
    }
}


