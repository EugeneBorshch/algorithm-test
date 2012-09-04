package com.eugeneborshch.algorithm.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Calculate number of array inversions using updated merge sort(nlog(n)).
 * User: Eugene Borshch
 */

public class NumberOfArrayInversions {

    public List<String> getInversions(int[] sourceArray) {
        List<String> result = new ArrayList<String>();

        find(sourceArray, result);
        return result;
    }


    private int[] find(int[] array, List<String> result) {
        if (array.length <= 1) {
            return array;
        }

        int midPoint = array.length / 2;
        int[] firstPart = Arrays.copyOfRange(array, 0, midPoint);
        int[] secondPart = Arrays.copyOfRange(array, midPoint, array.length);


        merge(find(firstPart, result), find(secondPart, result), result);
        return array;
    }


    /**
     * The idea is to have merge sort algorithm without actual sorting.
     * Instead we will calculate inversions.
     */
    private void merge(int[] firstArray, int[] secondArray, List<String> result) {
        int end = firstArray.length + secondArray.length;

        int i = 0;
        int j = 0;

        for (int k = 0; k < end; k++) {

            if (i < firstArray.length && j < secondArray.length) {
                if (firstArray[i] > secondArray[j]) {
                    /*
                    * The inversions are elements in firstArray that are still not processed  .
                    * This sub loop is required only to collect inversion pairs. In case when we
                    * need just to count the number of inversions this loop could be omitted.
                    */
                    for (int t = i; t < firstArray.length; t++) {
                        result.add("(" + firstArray[t] + "," + secondArray[j] + ")"); //inversion
                    }
                    j++;

                } else if (firstArray[i] < secondArray[j]) {
                    i++;
                } else {
                    i++;
                }
            } else if (i >= firstArray.length && j < secondArray.length) {
                j++;
            } else if (j >= secondArray.length && i < firstArray.length) {
                i++;
            }
        }

    }


    public static void main(String[] args) {
        System.out.println(new NumberOfArrayInversions().getInversions(new int[]{1, 3, 8, 5, 7, 2, 4}));
        System.out.println(new NumberOfArrayInversions().getInversions(new int[]{1, 3, 5, 2, 4, 6}));
    }

}
