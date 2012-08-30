package test.java.com.eugeneborshch.algorithm.interview;

import main.java.com.eugeneborshch.algorithm.interview.ClosestPair;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Eugene Borshch
 */
public class ClosestPairTest {

    ClosestPair.Point[] points;

    @Before
    public void init() {
        points = new ClosestPair.Point[]{new ClosestPair.Point(1, 1), new ClosestPair.Point(0, 5)
                , new ClosestPair.Point(5, 3), new ClosestPair.Point(2, 1), new ClosestPair.Point(10, 1),
                new ClosestPair.Point(3, 4), new ClosestPair.Point(7, 14), new ClosestPair.Point(13, 12),
                new ClosestPair.Point(2, 13), new ClosestPair.Point(6, 6), new ClosestPair.Point(8, 9)};
    }

    @Test
    public void testGetClosest() {
        System.out.println(new ClosestPair().getClosestPair(points));
    }
}
