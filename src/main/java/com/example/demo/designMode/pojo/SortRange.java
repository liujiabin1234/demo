package com.example.demo.designMode.pojo;

import com.example.demo.designMode.Service.ISortRecord;

public class SortRange {
    private long start;
    private long end;
    private ISortRecord sortRecord;

    public SortRange(long start, long end, ISortRecord sortRecord) {
        this.start = start;
        this.end = end;
        this.sortRecord = sortRecord;
    }

    public ISortRecord getSortAlg() {
        return this.sortRecord;
    }

    public boolean inRange(long fileSize) {
        return fileSize >= start && fileSize < end;
    }
}
