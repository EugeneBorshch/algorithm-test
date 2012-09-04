package com.eugeneborshch.algorithm.sorting;

import java.util.Arrays;

/**
 * Quick sort implementation
 * <p/>
 * User: Eugene Borshch
 */
public class QuickSort implements Sort {

    @Override
    public int[] sort(int[] source) {
        if (source == null)
            return null;

        source = Arrays.copyOf(source, source.length);
        int left = 0;
        int right = source.length;

        quicksort(source, left, right);

        return source;
    }

    private void quicksort(int[] source, int left, int right) {

        if (right - left <= 1)
            return;

        int pivot = source[left];
        int i = left + 1;//cause 1 is a pivot. Otherwise this should be changed.

        for (int j = left + 1; j < right; j++) {
            if (pivot > source[j]) {
                int tmp = source[j];
                source[j] = source[i];
                source[i] = tmp;
                i++;
            }
        }

        int tmp = source[left];
        source[left] = source[i - 1];
        source[i - 1] = tmp;


        quicksort(source, left, pivot);
        quicksort(source, pivot + 1, source.length);
    }


}
