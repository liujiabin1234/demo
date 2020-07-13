package com.example.demo.designMode.Service.impl;

import com.example.demo.designMode.Service.ISortRecord;

public class ExternalSort implements ISortRecord {
    @Override
    public void sort(String path) {
        System.out.println(this.toString());
    }
}