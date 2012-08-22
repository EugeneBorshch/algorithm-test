package test.java.com.eugeneborshch.algorithm.sorting;

import main.java.com.eugeneborshch.algorithm.sorting.MergeSort;
import main.java.com.eugeneborshch.algorithm.sorting.Sort;

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
