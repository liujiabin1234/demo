package com.example.demo.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallableTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //创建一个Callable，3秒后返回String类型
        Callable myCallable = new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                System.out.println("calld方法执行了");
                return "call方法返回值";
            }
        };
        System.out.println("提交任务之前 " + getStringDate());
        Future future = executor.submit(myCallable);
        System.out.println("提交任务之后，获取结果之前 " + getStringDate());
        System.out.println("获取返回值: " + future.get());
        System.out.println("获取到结果之后 " + getStringDate());
        executor.shutdown();
    }

    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
