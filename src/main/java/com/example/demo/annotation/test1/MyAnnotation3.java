package com.example.demo.annotation.test1;


import java.lang.annotation.*;

/**
 * MyAnnotation3注解可以用在方法上
 * 注解运行期也保留
 * 可继承
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyAnnotation3 {
    TranscationModel[] models() default TranscationModel.ReadWrite;
}


