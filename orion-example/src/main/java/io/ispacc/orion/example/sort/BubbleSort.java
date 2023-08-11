package io.ispacc.orion.example.sort;

import cn.hutool.core.util.ArrayUtil;

/**
 * <h3>冒泡排序</h3>
 * <p>与相邻元素比较，最大的往后移动</p>
 * <p>不稳定， 时间复杂度: O(n2), 空间复杂度: O(1) </p>
 */
public class BubbleSort {

    static void sort(int[] arr) {
        int length = arr.length;
        for (int i = length - 1; i > 0; i--) {
            // 如果本轮未进行任何更换，可判断已完成排序
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtil.swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
