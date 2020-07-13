package com.example.demo.designMode.factory;

import com.example.demo.designMode.Enum.SortTypeEnum;
import com.example.demo.designMode.Service.ISortRecord;

import java.io.File;

public class SortRecord2 {
    private static final long GB_SIZE = 1024 * 1024 * 1024;

    public void sortFile(String path) {
        // 省略校验逻辑
        File file = new File(path);
        long fileSize = file.length();
        ISortRecord sortAlg;
        //0-14G,使用内存排序，使用快排
        if (fileSize < 14 * GB_SIZE) {
            sortAlg = SortRecordFactory.getSortAlg(SortTypeEnum.quick_sort);
        } else if (fileSize < 20 * GB_SIZE) { // 14GB-20GB，使用外部排序
            sortAlg = SortRecordFactory.getSortAlg(SortTypeEnum.external_sort);
        } else if (fileSize < 100 * GB_SIZE) { // 20GB-100GB，使用并发的外部排序
            sortAlg = SortRecordFactory.getSortAlg(SortTypeEnum.concurrent_external_sort);
        } else { // 100GB往上，使用并行处理的mapReduceSort
            sortAlg = SortRecordFactory.getSortAlg(SortTypeEnum.map_reduce_sort);
        }
        sortAlg.sort(path);
    }

}