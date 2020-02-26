package com.example.demo.annotation.test3;


import java.lang.annotation.*;

/**
 * 非空注解：使用在方法的参数上，false表示此参数可以为空，true不能为空
 */
@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNotNull {
    boolean value() default false;
}


