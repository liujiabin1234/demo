package com.example.demo.designMode.factory;

import com.example.demo.designMode.Enum.SortTypeEnum;
import com.example.demo.designMode.Service.ISortRecord;
import com.example.demo.designMode.pojo.SortRange;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SortRecord3 {
    private static final long GB_SIZE = 1024 * 1024 * 1024;
    private List<SortRange> algList = new ArrayList<>();

    public SortRecord3() {
        algList.add(new SortRange(0, 14 * GB_SIZE, SortRecordFactory.getSortAlg(SortTypeEnum.quick_sort)));
        algList.add(new SortRange(14 * GB_SIZE, 20 * GB_SIZE, SortRecordFactory.getSortAlg(SortTypeEnum.external_sort)));
        algList.add(new SortRange(20 * GB_SIZE, 100 * GB_SIZE, SortRecordFactory.getSortAlg(SortTypeEnum.concurrent_external_sort)));
        algList.add(new SortRange(100 * GB_SIZE, Long.MAX_VALUE, SortRecordFactory.getSortAlg(SortTypeEnum.map_reduce_sort)));
    }

    public void sortFile(String path) {
        // 省略校验逻辑
        File file = new File(path);
        long fileSize = file.length();
        ISortRecord sortAlg = null;
        for (SortRange sortRange : algList) {
            if (sortRange.inRange(fileSize)) {
                sortAlg = sortRange.getSortAlg();
                break;
            }
        }
        if (sortAlg != null) {
            sortAlg.sort(path);
        } else {
            throw new IllegalArgumentException("according to file size ,can not find sort method");
        }
    }

}

