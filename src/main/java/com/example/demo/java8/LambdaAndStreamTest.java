package com.example.demo.java8;

import com.example.demo.shiro.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaAndStreamTest {

    //集合排序
    @Test
    public void test6() {
        List<String> list = Arrays.asList("2", "3", "4", "2", "8", "5", "1");
        //正序排序
        Collections.sort(list);
        //反转
        Collections.reverse(list);
        list.forEach(System.out::println);

        //指定对象字段排序
        List<User> userList = new ArrayList<User>();
        userList.add(new User("张三", 20));
        userList.add(new User("李四", 22));
        userList.add(new User("王五", 10));
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return t1.getAge() - user.getAge();
            }
        });
        userList.forEach(System.out::println);
    }

    @Test
    public void test5() {
        String[] dd = {"a", "b", "c"};
        Stream<String> stream = Arrays.stream(dd);
        List<String> list = stream.collect(Collectors.toList());
        Set<String> set = stream.collect(Collectors.toSet());
        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        String str = stream.collect(Collectors.joining(","));
        System.out.println(str);
    }

    @Test
    public void test4() {
        List<User> list2 = Arrays.asList(new User("红二"), new User("王三"), new User("刘四"));
        List<User> list = new ArrayList<>();
        User user = new User().setUsername("小张");
        list.add(user);
        list.addAll(list2);
        List<String> personList2 = list.stream().map(User::getUsername).limit(2).collect(Collectors.toList());
        personList2.forEach(System.out::println);
    }

    @Test
    public void test3() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("aa");
        stringList.add("bb");
        stringList.add("adc");
        stringList.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<User> list = new ArrayList<com.example.demo.shiro.User>();
        list.add(new User("张三", 20));
        list.add(new User("李四", 22));
        list.add(new User("王五", 10));

        //stream
        List<String> collect = list.stream().filter(user -> user.getAge() > 10).map(User::getUsername).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

}