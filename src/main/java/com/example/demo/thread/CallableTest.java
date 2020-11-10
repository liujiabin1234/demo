package com.example.demo.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallableTest {

    //测试future.get()方法是否会阻断主线程执行
    //结论：会，如果调用get()方法，只有子线程执行返回结果主线程才能继续执行，如果不调用get()方法主线程执行则无需等待子线程返回结果
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //创建一个Callable，3秒后返回String类型
        Callable myCallable = new Callable() {
            @Override
            public String call() throws Exception {
                //测试关键点
                Thread.sleep(3000);
                System.out.println("calld方法执行了");
                return "call方法返回值";
            }
        };
        //关闭线程池
        executor.shutdown();
        System.out.println("提交任务之前");
        Future future = executor.submit(myCallable);
        System.out.println("提交任务之后");
        System.out.println("获取返回值: " + future.get());
        System.out.println("子线程执行完毕，主线程继续执行。。。。。。");
    }
}
