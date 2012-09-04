package com.eugeneborshch.algorithm.sorting;

/**
 * User: EBorshch
 * Date: 21.08.12
 * Time: 16:30
 */
public class MergeSortTest extends AbstractSortTest {

    @Override
    protected Sort getSortAlgorithm() {
        return new MergeSort();
    }
}
