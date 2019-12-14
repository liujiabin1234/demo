package com.example.demo.java8;

import java.util.stream.Stream;

public class Java8TesterService2 {

    public static void main(String[] args) {
        int sumSize = Stream.of("1", "2", "3", "4", "5").parallel().map(s -> s.length()).reduce(Integer::sum).get();
        System.out.println(sumSize);
//        assertEquals(sumSize, 21);
    }

    public static class Tes {
        private String name;

        public Tes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}