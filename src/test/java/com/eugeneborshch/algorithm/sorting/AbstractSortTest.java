package com.eugeneborshch.algorithm.sorting;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;

/**
 * User: EBorshch
 */
public abstract class AbstractSortTest {

    protected int[] sourceEven;
    protected int[] sourceOdd;

    @Before
    public void setUp() {
        sourceEven = initRandomArray(100);
        sourceOdd = initRandomArray(99);
    }

    private int[] initRandomArray(int capacity) {
        int[] result = new int[capacity];
        ArrayList<Integer> tmp = new ArrayList<Integer>(capacity);

        for (int i = 0; i < capacity; i++) {
            tmp.add(i);
        }

        Collections.shuffle(tmp);

        for (int i = 0; i < tmp.size(); i++) {
            result[i] = tmp.get(i);
        }
        return result;
    }

    @Test
    public void testEvenArraySorting() {

        int[] sourceCopy = Arrays.copyOf(sourceEven, sourceEven.length);
        int[] arrayToTest = getSortAlgorithm().sort(sourceEven);

        Arrays.sort(sourceCopy);
        assertArrayEquals(sourceCopy, arrayToTest);

    }

    @Test
    public void testOddArraySorting() {

        int[] sourceCopy = Arrays.copyOf(sourceOdd, sourceOdd.length);
        int[] arrayToTest = getSortAlgorithm().sort(sourceOdd);

        Arrays.sort(sourceCopy);
        assertArrayEquals(sourceCopy, arrayToTest);
    }

    @Test
    public void testEmptyArraySorting() {
        int[] arrayToTest = getSortAlgorithm().sort(new int[0]);

        assertArrayEquals(new int[0], arrayToTest);
    }

    @Test
    public void testNullArraySorting() {
        int[] arrayToTest = getSortAlgorithm().sort(null);

        assertArrayEquals(null, arrayToTest);
    }

    protected abstract Sort getSortAlgorithm();
}
