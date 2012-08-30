package test.java.com.eugeneborshch.algorithm.sorting;

import main.java.com.eugeneborshch.algorithm.sorting.QuickSort;
import main.java.com.eugeneborshch.algorithm.sorting.Sort;

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
