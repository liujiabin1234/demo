package com.example.demo.annotation.test1;

import java.lang.annotation.*;

/**

 *  MyAnnotation2注解可以用在方法上
 *  注解运行期也保留
 *  不可继承
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation2 {
    TranscationModel model();
}


