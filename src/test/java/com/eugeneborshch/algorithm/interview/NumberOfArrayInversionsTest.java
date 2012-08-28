package test.java.com.eugeneborshch.algorithm.interview;

import main.java.com.eugeneborshch.algorithm.interview.NumberOfArrayInversions;

import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import java.util.List;

/**
 * Test NumberOfArrayInversions implementation
 * User: Eugene Borshch
 */
public class NumberOfArrayInversionsTest {


    @Test
    public void testInversions() {
        int[] source = new int[]{1, 3, 8, 5, 7, 2, 4, 6};
        List<String> inversions = new NumberOfArrayInversions().getInversions(source);
        assertTrue(3== inversions.size());
        System.out.println("Inersions :" + inversions);


    }
}
