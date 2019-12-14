package com.example.demo.java8;

public class Java8TesterService {

    public static void main(String args[]){
        Java8Tester tester = new Java8Tester();

        // 类型声明
        Java8Tester.MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        Java8Tester.MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        Java8Tester.MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        Java8Tester.MathOperation division = (int a, int b) -> a / b;

        System.out.println(addition.operation(3,2));
//        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
//        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
//        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
//        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        Java8Tester.GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        Java8Tester.GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }
}
