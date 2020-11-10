package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * 多线程工具类
 */
public class CommonThreadUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CommonThreadUtil.class);

    /**
     * @param dataList   需要处理的数据
     * @param maxThread  线程数
     * @param threadSize 每个线程处理数据数
     * @param taskPool   线程池
     * @param function   线程执行函数
     * @param <T>        函数输入值
     * @param <R>        函数返回值
     * @return
     */
    public static <T, R> List<R> runnerOperation(final List<T> dataList,
                                                 final int maxThread,
                                                 int threadSize,
                                                 ThreadPoolTaskExecutor taskPool,
                                                 final Function<List<T>, List<R>> function) {
        LOG.info("CommonThreadUtil config threadCount is {},threadSize is {}", maxThread, threadSize);

        List<List<T>> splitList = ListSplitUtil.splitList(dataList, threadSize);
        List<R> resDataList = new ArrayList<>();
        if (splitList == null) {
            LOG.error("request data is null return null");
            return null;
        }
        if (splitList.size() == 1) {
            //就一个任务，不用开启线程就能搞定
            resDataList = function.apply(dataList);
        } else {
            // 多线程分配策略
            final List<Future<String>> results = new ArrayList<>();
            final AtomicInteger taskCounter = new AtomicInteger(0);
//            final CountDownLatch countDownLatch = new CountDownLatch(splitList.size());
            for (List<T> currentBatchProcess : splitList) {
                results.add(taskRunner(currentBatchProcess, maxThread, taskPool, taskCounter, resDataList, function));
            }
            // 方式一：通过获取线程返回值，阻塞主线程
            for (final Future<String> result : results) {
                try {
                    //获取线程执行结果，将会阻塞主线程执行，主线程需等待子线程执行结束才能继续执行，取决于业务需要采取此方法
                    String threadResult = result.get();
                    System.out.println(Thread.currentThread().getName() + "返回值" + threadResult);
                } catch (final Exception e) {
                    final String errorMsg = "CommonThreadUtil thread happened error!" + e.getMessage();
                    LOG.error(errorMsg, e);
                } finally {
//                    countDownLatch.countDown();
                }
            }
            // 方式二：通过await()方法，阻塞主线程
//            try {
//                System.out.println("主线程阻塞,等待所有子线程执行完成");
//                boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
//                System.out.println("所有线程执行完成，结果：" + await);
//            } catch (Exception e) {
//                LOG.error("阻塞等待异常，{}", e.getMessage());
//            }
        }
        return resDataList;
    }

    /**
     * @param preProcessDatas
     * @param taskTotal
     * @param taskCounter
     * @param resDataList
     * @return
     */
    public static <T, R> Future<String> taskRunner(final List<T> preProcessDatas,
                                                   final int taskTotal,
                                                   ThreadPoolTaskExecutor taskPool,
                                                   final AtomicInteger taskCounter,
                                                   final List<R> resDataList,
                                                   final Function<List<T>, List<R>> function) {

        final Callable taskRunner = new Callable() {
            @Override
            public String call() {
                taskCounter.incrementAndGet();
                try {
                    LOG.info("CommonThreadUtil start new thread,taskRunner process ({}) records.", preProcessDatas.size());
                    resDataList.addAll(function.apply(preProcessDatas));
                } catch (final Exception e) {
                    final String errorMsg = "CommonThreadUtil taskRunner erroroccured:" + e.getMessage();
                    LOG.error(errorMsg, e);
                } finally {
                    taskCounter.decrementAndGet();
                }
                return "success";
            }
        };
//        while (true) {
//            if (taskCounter.get() <= taskTotal) {
//                return taskPool.submit(taskRunner);
//            }
//        }
        return taskPool.submit(taskRunner);
    }
}