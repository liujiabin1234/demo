package com.example.demo.annotation.test4;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService logService;

//    @Transactional   //标明此方法需使用事务
//    @Rollback(false) //标明使用完此方法后事务不回滚,true时为回滚
    @Test
    public void testLogAspect() {
        logService.testLogAspect();
    }

}


