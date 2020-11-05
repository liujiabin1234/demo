package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FileSplitUtil
 * @Description:
 * @author: linmeihong
 * @date: 2019/10/09 16:43
 */
public class ListSplitUtil {

    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     *
     * @param source
     * @return
     */
    public static <T> List<List<T>> averageAssign(final List<T> source, final int n) {

        final List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n;
        final int number = source.size() / n;
        int offset = 0;
        for (int i = 0; i < n; i++) {
            final List<T> value;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * @param sourceList
     * @param perListSize
     * @return 子List集合
     * @author youlong.peng
     * @description 按指定大小，分隔集合List，将集合按规定个数分为n个部分
     */
    public static <T> List<List<T>> splitList(final List<T> sourceList, final int perListSize) {
        if (sourceList == null || sourceList.size() == 0 || perListSize < 1) {
            return null;
        }

        final List<List<T>> result = new ArrayList<List<T>>();

        final int size = sourceList.size();
        final int count = (size + perListSize - 1) / perListSize;

        for (int i = 0; i < count; i++) {
            final List<T> subList = sourceList.subList(i * perListSize, ((i + 1)
                    * perListSize > size ? size : perListSize * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
