package com.example.demo;


import com.example.demo.annotation.test1.*;
import org.junit.jupiter.api.Test;

public class Demo1Test {
    @Test
    public void list() throws Exception {
        //        获取类上的注解
        MyAnnotation1 annotation1 = Demo1.class.getAnnotation(MyAnnotation1.class);
        System.out.println(annotation1.name());//abc
    
        //        获取方法上的注解
        MyAnnotation2 myAnnotation2 = Demo1.class.getMethod("list").getAnnotation(MyAnnotation2.class);
        System.out.println(myAnnotation2.model());//Read

    }

    @Test
    public void edit() throws Exception {
        MyAnnotation3 myAnnotation3 = Demo1.class.getMethod("edit").getAnnotation(MyAnnotation3.class);
        for (TranscationModel model : myAnnotation3.models()) {
            System.out.println(model);//Read,Write
        }
    }
}


