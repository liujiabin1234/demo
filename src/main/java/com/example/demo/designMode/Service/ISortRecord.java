package com.example.demo.designMode.Service;

public interface ISortRecord {
    default void sort(String path) {
        System.out.println(this.toString());
    }

}
