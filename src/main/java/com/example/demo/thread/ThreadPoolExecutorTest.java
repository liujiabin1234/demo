package com.example.demo.thread;

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

    //方式一：阿里推荐
    private static final ThreadPoolExecutor threadPoolExector = new ThreadPoolExecutor(
            3, 10, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(100),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    //方式二：spring集成
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void test() {

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>> executeList = ListSplitUtil.splitList(integerList, 3);

//        threadPoolTaskExecutor.setCorePoolSize(10);
//        threadPoolTaskExecutor.setMaxPoolSize(20);
//        threadPoolTaskExecutor.setKeepAliveSeconds(10);
//        threadPoolTaskExecutor.setQueueCapacity(20);//DiscardPolicy
//        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        for (List<Integer> integers : executeList) {
            //开启线程
            threadPoolExector.execute(() -> {
                for (Integer i : integers) {
                    System.out.println("当前线程" + Thread.currentThread().getName() + "执行：" + i);
                }
            });
        }
        //建议主动关闭线程池
        threadPoolExector.shutdown();
    }

}
