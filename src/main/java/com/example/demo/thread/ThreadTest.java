package com.example.demo.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {

    @Test
    public void test() {
        Res res = new Res();
        IntThrad intThrad = new IntThrad(res);
        OutThread outThread = new OutThread(res);
        intThrad.start();
        outThread.start();

    }
}
