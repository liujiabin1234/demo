package com.example.demo.thread;

import com.example.demo.redis.RedisCacheClient;
import com.example.demo.utils.ListSplitUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolExecutorTest {

    //int corePoolSize, - 线程池维护线程的最少数量 （core : 核心）
    //int maximumPoolSize, - 线程池的最大线程数。
    //long keepAliveTime, - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
    //TimeUnit unit, - keepAliveTime 的时间单位。
    //BlockingQueue<Runnable> workQueue, - 用来储存等待执行任务的队列。
    //RejectedExecutionHandler handler)  - 拒绝策略。
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            1, 2, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(20),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Autowired
    private RedisCacheClient redisCacheClient;

    @Test
    public void test2() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>> executeList = ListSplitUtil.splitList(integerList, 3);

        for (List<Integer> integers : executeList) {
            threadPool.execute(() -> {
                for (Integer i : integers) {
                    System.out.println("当前执行：" + i);
                }
            });
        }
    }

}
