package com.example.demo.rushPurchase;

import com.example.demo.redis.RedisCacheClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class rushPurchase {

    private int count = 10;

    private static final ThreadPoolExecutor threadPoolExector = new ThreadPoolExecutor(
            1, 3, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(100),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisCacheClient redisCacheClient;

    @Test
    public void test() {
        int i = 1;
        while (count > 0) {
            System.out.println("循环次数：" + i++ + "当前count" + count);
            threadPoolExector.execute(() -> {
                this.print();
            });
        }
        System.out.println("最终" + count);
    }

    public synchronized void print() {
        System.out.println(Thread.currentThread().getName() + " count=" + count);
        count--;
    }


    @Test
    public void test2() throws InterruptedException {
//        int i = 1;
//        int j = 1;
//        while (j > 0) {
//            System.out.println("当前循环" + i);
//            i++;
            String stringKey = redisCacheClient.opsForValue().get("StringKey");
            RLock rLock = redissonClient.getLock("lj:lock:o2ot:ow-order:pay");
            // 尝试加锁，最多等待1秒，上锁以后1分钟自动解锁，tryLock方法有返回值
            boolean b = rLock.tryLock(1, 1, TimeUnit.MINUTES);
            if (b) {
                redisCacheClient.opsForValue().set("StringKey", String.valueOf(Integer.valueOf(stringKey) - 1));
//                j = Integer.valueOf(String.valueOf(Integer.valueOf(stringKey) - 1));
                this.unlock("lj:lock:o2ot:ow-order:pay");
                System.out.println("当前剩余值：" + stringKey);
            } else {
                System.out.println("当前锁定中，"  + "当前count" + stringKey);
            }
        }


    public void unlock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            //判断线程是否持有锁，因为有可能程序执行期间锁自动释放或被别的线程释放，当主动释放的时候会报错：Caused by: java.lang.IllegalMonitorStateException: attempt to unlock lock, not locked by current thread by node id
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        } catch (Throwable e) {
            String msg = String.format("UNLOCK FAILED: key=%s", lockKey);
            throw new IllegalStateException(msg, e);
        }
    }
}
