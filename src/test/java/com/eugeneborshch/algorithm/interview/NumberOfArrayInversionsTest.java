package com.eugeneborshch.algorithm.interview;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Test NumberOfArrayInversions implementation
 * User: Eugene Borshch
 */
public class NumberOfArrayInversionsTest {


    @Test
    public void testInversions() {
        int[] source = new int[]{1, 4, 2, 3};
        List<String> inversions = new NumberOfArrayInversions().getInversions(source);
        assertEquals(2, inversions.size());
        System.out.println("Inersions :" + inversions);


    }
}
