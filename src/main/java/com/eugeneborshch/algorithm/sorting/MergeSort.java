package main.java.com.eugeneborshch.algorithm.sorting;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import java.util.Arrays;

/**
 * Merge sorting implementation.
 * <p/>
 * User: EBorshch
 */
public class MergeSort implements Sort {

    public int[] sort(int[] source) {

        if (source.length <= 1) {
            return source;
        }

        int midpoint = source.length / 2;
        int[] firstRange = Arrays.copyOfRange(source, 0, midpoint);
        int[] secondRange = Arrays.copyOfRange(source, midpoint, source.length);
        return merge(sort(firstRange), sort(secondRange));

    }

    private int[] merge(int[] firstRange, int[] secondRange) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] result = new int[firstRange.length + secondRange.length];

        while (k != result.length) {

            boolean firstEnded = i >= firstRange.length;
            boolean secondEnded = j >= secondRange.length;

            boolean addFirst = !firstEnded && !secondEnded && firstRange[i] <= secondRange[j];

            boolean addSecond = !firstEnded && !secondEnded && firstRange[i] >= secondRange[j];

            addFirst = !addFirst && !addSecond ? !firstEnded && secondEnded : addFirst;
            addSecond = !addFirst && !addSecond ? !secondEnded && firstEnded : addSecond;


            if (addFirst) {
                result[k] = firstRange[i];
                i++;
                k++;
            }

            if (addSecond) {
                result[k] = secondRange[j];
                j++;
                k++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MergeSort().sort(new int[]{5, 7, 92, 3, 0, 2, 1, 5, 4})));
    }
}
