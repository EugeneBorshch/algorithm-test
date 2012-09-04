package com.eugeneborshch.algorithm.sorting;

/**
 * QuickSort test
 * User: Eugene Borshch
 */
public class QuickSortTest extends AbstractSortTest {
    @Override
    protected Sort getSortAlgorithm() {
        return new QuickSort();
    }
}
