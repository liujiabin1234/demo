package com.example.demo.annotation.test1;


import java.lang.annotation.*;

/**
 *
 * MyAnnotation1注解可以用在类、接口、属性、方法上
 * 注解运行期也保留
 * 不可继承
 */
@Target({ElementType.TYPE, ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation1 {
    String name();
}


