package com.example.demo.java8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.shiro.User;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaAndStreamTest {

    @Test
    public void test13() {
        User tom2 = new User("Tom", 1);
        User tom1 = new User("Tom", 2);
        User tom = new User("Tom", 3);
        User dick2 = new User("Dick", 3);
        User dick1 = new User("Dick", 3);
        User dick = new User("Dick", 3);
        List<User> users = new ArrayList<>();
        users.add(tom);
        users.add(tom2);
        users.add(tom1);
        users.add(dick);
        users.add(dick2);
        users.add(dick1);
        final String username = users.stream().map(User::getUsername).collect(Collectors.joining(","));
        System.out.println(username);
    }

    @Test
    public void test12() {
        User tom2 = new User("Tom", 1);
        User tom1 = new User("Tom", 2);
        User tom = new User("Tom", 3);
        User dick2 = new User("Dick", 3);
        User dick1 = new User("Dick", 3);
        User dick = new User("Dick", 3);
        List<User> users = new ArrayList<>();
        users.add(tom);
        users.add(tom2);
        users.add(tom1);
        users.add(dick);
        users.add(dick2);
        users.add(dick1);
        Map<String, Integer> dotMap = users.stream().collect(Collectors.groupingBy(User::getUsername, Collectors.summingInt(User::getAge)));
        System.out.println(JSONObject.toJSONString(dotMap));
    }

    @Test
    public void test11() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "");
        String num = map.getOrDefault("num", "5000");
        System.out.println(num);
    }

    @Test
    public void test10() {
        final String resStr = "{\"username\":\"Tom\",\"password\":\"123456\"}";
        final JSONObject jsonObject = JSONObject.parseObject(resStr);
        final User user = JSON.toJavaObject(jsonObject, User.class);
        System.out.println(JSONObject.toJSONString(user));
    }

    @Test
    public void test9() {
        BigDecimal bigDecimal = NumberUtils.createBigDecimal("1");
        BigDecimal bigDecimal1 = new BigDecimal("1");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal1);
    }

    @Test
    public void test8() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Tom", 10));
        users.add(new User("Tom2", 20));
        users.add(new User("Tom3", 30));
        users.add(new User("Tom4", 40));
        users.removeIf(user -> (user.getAge() > 18));
        System.out.println(JSONArray.toJSONString(users));
    }

    @Test
    public void test7() {
        String onlineShopId = "";
//        Assert.isNull(onlineShopId, "online shop id could not be null");
        User user = new User();
        String s = Optional.ofNullable(user).map(User::getUsername).orElse(null);
        Integer integer = Optional.ofNullable(user).map(User::getAge).orElse(2);
//        Integer integer2 = Optional.ofNullable(user).map(User::getAge).orElseThrow(() -> new IllegalArgumentException("参数异常"));
        System.out.println(s + "/" + integer);
    }

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