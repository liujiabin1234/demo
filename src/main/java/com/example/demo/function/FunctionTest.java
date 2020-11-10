package com.example.demo.function;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.shiro.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class FunctionTest {

    @Test
    public void test() {
        //定义一个function 输入是String类型，输出是 User 类型，User是一个类。
        Function<String, User> times2 = fun -> {
            User a = new User();
            a.setUsername(fun);
            return a;
        };

        String[] testintStrings = {"1", "2", "3", "4"};

        //将String 的Array转换成map,调用times2函数进行转换
        Map<String, User> userMap = Stream.of(testintStrings).collect(Collectors.toMap(aa -> aa, username -> times2.apply(username)));
        System.out.println(JSONArray.toJSONString(userMap));
    }
}
