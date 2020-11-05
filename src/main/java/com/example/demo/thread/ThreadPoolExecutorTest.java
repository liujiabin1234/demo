package com.example.demo.thread;

import com.example.demo.redis.RedisCacheClient;
import com.example.demo.utils.ListSplitUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolExecutorTest {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    //int corePoolSize, - 线程池维护线程的最少数量 （core : 核心）
    //int maximumPoolSize, - 线程池的最大线程数。
    //long keepAliveTime, - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
    //TimeUnit unit, - keepAliveTime 的时间单位。
    //BlockingQueue<Runnable> workQueue, - 用来储存等待执行任务的队列。
    //RejectedExecutionHandler handler)  - 拒绝策略。

//    拒绝策略
//    rejectedExectutionHandler参数字段用于配置绝策略，常用拒绝策略如下
//    AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
//    CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
//    DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
//    DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            1, 2, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(20),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Autowired
    private RedisCacheClient redisCacheClient;

    @Test
    public void test2() {
//        taskExecutor.setCorePoolSize(10);
//        taskExecutor.setMaxPoolSize(20);
//        taskExecutor.setKeepAliveSeconds(10);
//        taskExecutor.setQueueCapacity(20);//DiscardPolicy
//        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>> executeList = ListSplitUtil.splitList(integerList, 3);

        for (List<Integer> integers : executeList) {
            threadPool.execute(() -> {
                for (Integer i : integers) {
                    System.out.println("当前执行：" + i);
                }
            });
        }
        threadPool.shutdown();
    }

}
