package io.ispacc.orion.example.sort;

import org.junit.jupiter.api.Test;

public class Main {
    // generate random array
    int[] arr = {4, 2, 1, 7, 978, 45, 3, 5, 34, 24, 55, 6, 7, 8, 4, 43, 5, 6464, 6, 46, 4, 90, 64, 64, 6};

    @Test
    public void selectionSort() {
        SelectionSort.sort(arr);
        print("选择排序");
    }


    @Test
    public void bubbleSort() {
        BubbleSort.sort(arr);
        print("冒泡排序");
    }

    @Test
    public void test1() {
    }

    private void print(String sortName) {
        System.out.print(sortName + ": ");
        for (int i : arr) {
            System.out.print(" " + i);
        }
        System.out.println();
    }
}
