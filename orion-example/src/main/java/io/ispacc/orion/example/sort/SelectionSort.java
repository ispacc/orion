package io.ispacc.orion.example.sort;


import cn.hutool.core.util.ArrayUtil;

/**
 * <h3>选择排序</h1>
 * <p>每轮从未排序区选择最小的元素放在已排序区的末尾</p>
 * <P>稳定, 时间复杂度：O(n2), 空间复杂度：O(1)</P>
 */
public class SelectionSort {

    static void sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (arr[i] > arr[j]) {
                    ArrayUtil.swap(arr, i, j);
                }
            }
        }
    }
}
