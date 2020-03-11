package com.example.demo;


import com.example.demo.annotation.test1.*;
import org.junit.jupiter.api.Test;

import java.util.*;

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

//    面试题：将集合去重并合并同一个人的手机号
    @Test
    public void listTest(){
        Set<String> arrayList = new HashSet<>();
        arrayList.add("1,151");
        arrayList.add("1,139");
        arrayList.add("1,151");
        arrayList.add("2,139");
        arrayList.add("3,173");
        arrayList.add("3,183");

        Map<String, String> hashMap = new HashMap<>();
        for (String s : arrayList) {
            String[] split = s.split(",");
            String put = hashMap.put(split[0], split[1]);
            if (put != null) {
                hashMap.put(split[0], put + "," + split[1]);
            }
        }
        System.out.println(hashMap);




    }
}


