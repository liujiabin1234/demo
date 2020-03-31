package com.example.demo.java8Character;

import com.example.demo.shiro.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaAndStreamTest {

    @Test
    public void test2() {
        List<User> users = new ArrayList<User>();
        users.add(new User("张三", 20));
        users.add(new User("李四", 22));
        users.add(new User("王五", 10));

        //lambda表达式
//        users.forEach((User user) -> System.out.println(user.getUsername()));
//        users.forEach((User user) -> System.out.println(user.getPassword()));

        //stream
        List<String> collect = users.stream().filter(user -> user.getAge()>10).map(User::getUsername).collect(Collectors.toList());
        collect.forEach(str -> System.out.println(str));

    }

}