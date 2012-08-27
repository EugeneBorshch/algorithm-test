package main.java.com.eugeneborshch.algorithm.interview;


import java.util.Arrays;

/**
 * Find closest points on the plane with a nlogn time.
 * <p/>
 * User: Eugene Borshch
 */
public class ClosestPair {


    public String getClosestPair(Point[] sourcePoints) {

        Point[] sortedByX = new MergePointsSort().sort(sourcePoints, new Rule() {
            public int getPointValue(Point point) {
                return point.getX();
            }
        });


        Point[] sortedByY = new MergePointsSort().sort(sourcePoints, new Rule() {
            public int getPointValue(Point point) {
                return point.getY();
            }
        });

        return "";
    }


    private static class MergePointsSort {

        public Point[] sort(Point[] points, Rule rule) {
            if (points.length == 1) {
                return points;
            }

            int midPoint = points.length / 2;
            Point[] leftSubArray = Arrays.copyOfRange(points, 0, midPoint);
            Point[] rightSubArray = Arrays.copyOfRange(points, midPoint, points.length);
            return merge(sort(leftSubArray, rule), sort(rightSubArray, rule), rule);
        }

        private Point[] merge(Point[] leftSubArray, Point[] rightSubArray, Rule rule) {
            int i = 0;
            int j = 0;
            int k = 0;

            Point[] mergedArray = new Point[leftSubArray.length + rightSubArray.length];

            while (k < mergedArray.length) {
                if (i < leftSubArray.length && j < rightSubArray.length) {

                    int leftValue = rule.getPointValue(leftSubArray[i]);
                    int rightValue = rule.getPointValue(rightSubArray[j]);
                    if (leftValue < rightValue) {
                        mergedArray[k] = leftSubArray[i];
                        i++;
                        k++;
                    } else if (leftValue > rightValue) {
                        mergedArray[k] = rightSubArray[j];
                        j++;
                        k++;
                    } else if (leftValue == rightValue) {
                        mergedArray[k] = leftSubArray[i];
                        i++;
                        k++;
                        mergedArray[k] = rightSubArray[j];
                        j++;
                        k++;
                    }

                } else if (i < leftSubArray.length && j >= rightSubArray.length) {
                    mergedArray[k] = leftSubArray[i];
                    i++;
                    k++;
                } else if (i >= leftSubArray.length && j < rightSubArray.length) {
                    mergedArray[k] = rightSubArray[i];
                    j++;
                    k++;
                }
            }
            return mergedArray;
        }
    }


    private interface Rule {
        int getPointValue(Point point);
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
