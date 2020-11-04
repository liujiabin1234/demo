package com.example.demo;

import com.example.demo.mybatis.service.LargeCountService;
import com.example.demo.redis.RedisCacheClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LargeCountService largeCountService;
    @Autowired
    private RedisCacheClient redisCacheClient;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test5() {
        if (redisCacheClient.hasKey("三国2")) {
            redisCacheClient.delete("三国2");
        }
        redisCacheClient.boundZSetOps("三国2").add("曹操", 1);
    }

    @Test
    public void test4() throws InterruptedException {
        RLock rLock = redissonClient.getLock(String.format("lj:lock:o2ot:ow-order:pay:%s", "Tom"));
//        rLock.lock();
//        rLock.lock(1, TimeUnit.MINUTES);
        // 尝试加锁，最多等待1秒，上锁以后1分钟自动解锁，tryLock方法有返回值
        boolean b = rLock.tryLock(1, 1, TimeUnit.MINUTES);
        System.out.println(b);
        unlock(String.format("lj:lock:o2ot:ow-order:pay:%s", "Tom"));
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


    @Test
    public void test3() {

        redisCacheClient.boundZSetOps("三国2").add("曹操", 1);
        redisCacheClient.boundZSetOps("三国2").add("曹操2", 2);
        redisCacheClient.boundZSetOps("三国2").add("曹操3", 3);
        redisCacheClient.boundZSetOps("三国2").add("曹操4", 4);
        redisCacheClient.boundZSetOps("三国2").add("曹操5", 5);
        //按照分数正序排序
        redisCacheClient.opsForZSet().rangeByScoreWithScores("三国2", 1, 50, 0, 3);
        //按照分数倒序排序
        redisCacheClient.opsForZSet().reverseRangeByScoreWithScores("三国2", 1, 50, 0, 3);

        redisCacheClient.boundSetOps("三国").add("刘备");
        redisCacheClient.boundSetOps("三国").add("孙权");
        redisCacheClient.boundSetOps("三国").members();
        redisCacheClient.boundSetOps("三国").remove("孙权");
        redisCacheClient.delete("三国");

        redisCacheClient.boundListOps("桃园三结义").rightPush("刘备");
        redisCacheClient.boundListOps("桃园三结义").rightPush("关羽");
        redisCacheClient.boundListOps("桃园三结义").rightPush("张飞");
        redisCacheClient.boundListOps("桃园三结义").range(0, 10);
        redisCacheClient.boundListOps("桃园三结义").index(1);
        redisCacheClient.boundListOps("桃园三结义").remove(1, "关羽");

        redisCacheClient.boundHashOps("西游记").put("老大", "唐僧");
        redisCacheClient.boundHashOps("西游记").put("老二", "悟空");
        redisCacheClient.boundHashOps("西游记").put("老三", "八戒");
        redisCacheClient.boundHashOps("西游记").put("老四", "沙僧");
        redisCacheClient.boundHashOps("西游记").keys();
        redisCacheClient.boundHashOps("西游记").values();
        redisCacheClient.boundHashOps("西游记").get("老四");
        redisCacheClient.boundHashOps("西游记").delete("c", "a");
        redisCacheClient.boundHashOps("西游记").delete();

    }

    @Test
    public void contextLoads() {
        String str = "Java string-split#test";
        String[] split = str.split(" |-|#");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @Test
    public void queryLargeCountByIdTest() {
//        LargeCount largeCount = largeCountService.queryById(1);
//        System.out.println(largeCount);
    }
}
