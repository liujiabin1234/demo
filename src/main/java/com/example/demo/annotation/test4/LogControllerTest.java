package com.example.demo.annotation.test4;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogControllerTest {

    @Autowired
    private LogController logController;

//    @Transactional   //标明此方法需使用事务
//    @Rollback(false) //标明使用完此方法后事务不回滚,true时为回滚
    @Test
    public void testLogAspect() {
        logController.testLogAspect();
    }

}


