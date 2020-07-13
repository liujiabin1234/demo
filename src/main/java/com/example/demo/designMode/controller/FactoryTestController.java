package com.example.demo.designMode.controller;

import com.example.demo.designMode.factory.SortRecord2;
import com.example.demo.designMode.factory.SortRecord3;
import org.junit.Test;

public class FactoryTestController {



    //工厂模式
    @Test
    public void factoryTest() {
        SortRecord2 sorter = new SortRecord2();
        sorter.sortFile("");
    }

    //策略模式
    @Test
    public void strategyTest() {
        SortRecord3 sorter = new SortRecord3();
        sorter.sortFile("");
    }
}
