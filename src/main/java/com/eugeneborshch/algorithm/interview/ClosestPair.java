package main.java.com.eugeneborshch.algorithm.interview;


import java.util.Arrays;

/**
 * Find closest points on the plane with a nlogn time.
 * <p/>
 * User: Eugene Borshch
 */
public class ClosestPair {


    private String closest;

    public String getClosestPair(Point[] sourcePoints) {

        Point[] sortedByX = new MergePointsSort().sort(sourcePoints, new Rule() {
            public int getPointValue(Point point) {
                return point.getX();
            }
        });

        double minDistance = getClosest(sortedByX);
        return closest + "  distance : " + minDistance;
    }


    private double getClosest(Point[] sortedByX) {

        if (sortedByX.length == 1) {
            return Double.MAX_VALUE;
        }

        if (sortedByX.length <= 3) {
            return calculateClosestBruteForce(sortedByX);
        }

        int midPoint = sortedByX.length / 2;
        Point[] leftSubArray = Arrays.copyOfRange(sortedByX, 0, midPoint);
        Point[] rightSubArray = Arrays.copyOfRange(sortedByX, midPoint, sortedByX.length);

        return calculateCrossClosest(getClosest(leftSubArray), getClosest(rightSubArray), leftSubArray, rightSubArray);

    }

    private double calculateCrossClosest(double closestLeft, double closestRight, Point[] leftSubArray, Point[] rightSubArray) {

        double deltaX = Math.min(closestLeft, closestRight);

        //Points that belongs to strip:    Xmin-Delta <= Px < Xmin+Delta
        Point midPoint = leftSubArray[leftSubArray.length - 1];//as left sub-array is sorted we can take the last one

        int leftBoundIndx = -1;
        for (int i = 0; i < leftSubArray.length; i++) {
            if (leftSubArray[i].getX() >= midPoint.getX() - deltaX) {
                leftBoundIndx = i;
                break;
            }
        }


        int rightBoundIndx = -1;
        for (int i = 0; i < rightSubArray.length; i++) {
            if (rightSubArray[i].getX() <= deltaX + midPoint.getX()) {
                rightBoundIndx = i;
                break;
            }
        }

        /*
         * If no points from left or right sub-arrays doesn't belong to the strip described above then we have a
         * best case when closest pair lies in left or right subsets.
         *
         */
        if (rightBoundIndx == -1 || leftBoundIndx == -1) {
            return deltaX;
        }

        /*
        *  Otherwise there is possibility that closest pair is formed by one point in the left and one point in the right subsets.
        */

        int leftStripLength = leftSubArray.length - leftBoundIndx;
        int rightStripLength = rightBoundIndx + 1;
        int k = 0;

        //Lets fill the strip with points Xmin-Delta <= Px < Xmin+Delta
        Point[] sortedByYStrip = new Point[leftStripLength + rightStripLength];

        for (int i = leftSubArray.length - 1; i >= leftSubArray.length - leftStripLength; i--) {
            sortedByYStrip[k] = leftSubArray[i];
            k++;
        }

        for (int i = 0; i < rightStripLength; i++) {
            sortedByYStrip[k] = rightSubArray[i];
            k++;
        }


        //Sort the points in the strip by Y coordinate

        sortedByYStrip = new MergePointsSort().sort(sortedByYStrip, new Rule() {
            public int getPointValue(Point point) {
                return point.getY();
            }
        });

        for (int i = 0; i < sortedByYStrip.length; i++) {
            int j = i + 1;
            while (j - i <= 6 && j < sortedByYStrip.length) {

                double distance = getDistance(sortedByYStrip[i], sortedByYStrip[j]);
                if (distance < deltaX) {
                    deltaX = distance;
                    closest = sortedByYStrip[i] + " - " + sortedByYStrip[j];
                }

                j++;
            }
        }
        return deltaX;

    }


    private double calculateClosestBruteForce(Point[] sortedByX) {
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < sortedByX.length; i++) {
            for (int j = 1; j < sortedByX.length; j++) {
                //we don't want to calculate distance between the same point
                if (i != j) {
                    double distance = getDistance(sortedByX[i], sortedByX[j]);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closest = sortedByX[i] + " - " + sortedByX[j];
                    }
                }
            }
        }
        return minDistance;
    }

    private double getDistance(Point pointOne, Point pointTwo) {
        return Math.sqrt(Math.pow(pointTwo.getX() - pointOne.getX(), 2) + Math.pow(pointTwo.getY() - pointOne.getY(), 2));

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
                    mergedArray[k] = rightSubArray[j];
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
