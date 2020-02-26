package com.example.demo.annotation.test2;


public class Demo2 {
    @TestAnnotation(value = "这就是value对应的值_msg1", what = "这就是what对应的值_msg1")
    private static String msg1;

    @TestAnnotation("这就是value对应的值1")
    private static String msg2;

    @TestAnnotation(value = "这就是value对应的值2")
    private static String msg3;

    @TestAnnotation(what = "这就是what对应的值")
    private static String msg4;
}


