package com.example.demo.annotation.test3;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Parameter;

public class Demo3Test {

    @Test
    public void hello1() throws Exception {
        for (Parameter parameter : Demo3.class.getMethod("hello1",String.class,String.class).getParameters()) {
            IsNotNull annotation = parameter.getAnnotation(IsNotNull.class);
            if (annotation != null) {
                System.out.println(annotation.value());//true
            }
        }
    }

    @Test
    public void hello2() throws Exception {
        for (Parameter parameter : Demo3.class.getMethod("hello2", String.class).getParameters()) {
            IsNotNull annotation = parameter.getAnnotation(IsNotNull.class);
            if (annotation != null) {
                System.out.println(annotation.value());//false
            }
        }
    }
}


