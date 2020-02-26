package com.example.demo.annotation.test3;


/**
 * 获取参数修饰注解对应的属性值
 */
public class Demo3 {

    public void hello1(@IsNotNull(true) String name,@IsNotNull String age) {
        System.out.println("hello:" + name);
    }

    public void hello2(@IsNotNull String name) {
        System.out.println("hello:" + name);
    }
}


