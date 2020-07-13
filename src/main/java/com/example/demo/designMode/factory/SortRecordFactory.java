package com.example.demo.designMode.factory;

import com.example.demo.designMode.Enum.SortTypeEnum;
import com.example.demo.designMode.Service.ISortRecord;
import com.example.demo.designMode.Service.impl.ConcurrentExternalSort;
import com.example.demo.designMode.Service.impl.ExternalSort;
import com.example.demo.designMode.Service.impl.MapReduceSort;
import com.example.demo.designMode.Service.impl.QuickSort;

import java.util.HashMap;
import java.util.Map;

public class SortRecordFactory {
    private static final Map<SortTypeEnum, ISortRecord> map = new HashMap<>();

    static {
        map.put(SortTypeEnum.quick_sort, new QuickSort());
        map.put(SortTypeEnum.external_sort, new ExternalSort());
        map.put(SortTypeEnum.concurrent_external_sort, new ConcurrentExternalSort());
        map.put(SortTypeEnum.map_reduce_sort, new MapReduceSort());
    }

    public static ISortRecord getSortAlg(SortTypeEnum type) {
        if (type == null) {
            throw new IllegalArgumentException("type can not be null");
        }
        return map.get(type);
    }
}
