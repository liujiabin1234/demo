package com.example.demo.annotation.test2;

import org.junit.jupiter.api.Test;

public class Demo2Test {
    @Test
    public void test1() throws Exception {
        TestAnnotation msg1 = Demo2.class.getDeclaredField("msg1").getAnnotation(TestAnnotation.class);
        System.out.println(msg1.value());
        System.out.println(msg1.what());
    }

    @Test
    public void test2() throws Exception {
        TestAnnotation msg2 = Demo2.class.getDeclaredField("msg2").getAnnotation(TestAnnotation.class);
        System.out.println(msg2.value());
        System.out.println(msg2.what());
    }

    @Test
    public void test3() throws Exception {
        TestAnnotation msg3 = Demo2.class.getDeclaredField("msg3").getAnnotation(TestAnnotation.class);
        System.out.println(msg3.value());
        System.out.println(msg3.what());
    }

    @Test
    public void test4() throws Exception {
        TestAnnotation msg4 = Demo2.class.getDeclaredField("msg4").getAnnotation(TestAnnotation.class);
        System.out.println(msg4.value());
        System.out.println(msg4.what());
    }
}

