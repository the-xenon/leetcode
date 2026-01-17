/*
https://leetcode.com/problems/find-the-largest-area-of-square-inside-two-rectangles/description/?envType=daily-question&envId=2026-01-17

There exist n rectangles in a 2D plane with edges parallel to the x and y axis. You are given two 2D integer arrays bottomLeft and topRight where bottomLeft[i] = [a_i, b_i] and topRight[i] = [c_i, d_i] represent the bottom-left and top-right coordinates of the ith rectangle, respectively.

You need to find the maximum area of a square that can fit inside the intersecting region of at least two rectangles. Return 0 if such a square does not exist.
 */

import java.util.*;

public class n3047_FindLargestAreaInsideRectangles {
    public static void main(String[] args) {
        new n3047_FindLargestAreaInsideRectangles().run();
    }

    private void run() {
        System.out.println(largestSquareArea(new int [][]{{1,1},{2,2},{3,1}}, new int [][]{{3,3},{4,4},{6,6}}));
        System.out.println(largestSquareArea(new int [][]{{1,1},{1,3},{1,5}}, new int [][]{{5,5},{5,7},{5,9}}));
        System.out.println(largestSquareArea(new int [][]{{1,1},{2,2},{1,2}}, new int [][]{{3,3},{4,4},{3,4}}));
        System.out.println(largestSquareArea(new int [][]{{1,1},{3,3},{3,1}}, new int [][]{{2,2},{4,4},{4,2}}));
        System.out.println(largestSquareArea(new int [][]{{1,2},{1,2}}, new int [][]{{4,5},{2,3}}));
        System.out.println(largestSquareArea(new int [][]{{1,4},{1,1},{3,8}}, new int [][]{{6,9},{6,4},{8,10}}));
    }

    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {  // my
        int maxSide = 0;
        for (int i = 0; i < bottomLeft.length; i++) {
            int r1 = topRight[i][0], t1 = topRight[i][1];
            int l1 = bottomLeft[i][0], b1 = bottomLeft[i][1];
            for (int j = i + 1; j < bottomLeft.length; j++) {
                int r2 = topRight[j][0], t2 = topRight[j][1];
                int l2 = bottomLeft[j][0], b2 = bottomLeft[j][1];

                if (l2 > r1 || r2 < l1  ||  t2 < b1 || b2 > t1) {
                    continue;
                }
                int il = l2 > l1 ? l2 : l1;
                int ir = r2 < r1 ? r2 : r1;
                int ilen = ir - il;
                int ib = b1 > b2 ? b1 : b2;
                int it = t1 < t2 ? t1 : t2;
                int ih = it - ib;

                int squareSide = ih < ilen ? ih : ilen;
                if (squareSide > maxSide) {
                    maxSide = squareSide;
                }
            }
        }
        return (long)maxSide * maxSide;
    }

    public long largestSquareArea3(int[][] bottomLeft, int[][] topRight) {  //chatGPT
        int n = bottomLeft.length;
        int maxSide = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int minX = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int minY = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int maxX = Math.min(topRight[i][0], topRight[j][0]);
                int maxY = Math.min(topRight[i][1], topRight[j][1]);

                if (minX < maxX && minY < maxY) {
                    int side = Math.min(maxX - minX, maxY - minY);
                    maxSide = Math.max(maxSide, side);
                }
            }
        }

        return (long)maxSide * maxSide;
    }

    public long largestSquareArea4(int[][] bottomLeft, int[][] topRight) {  //deepSeek optimized
        int n = bottomLeft.length;
        long maxArea = 0;

        // For all possible x-coordinates (potential left edges of squares)
        Set<Integer> xCoordinates = new HashSet<>();
        for (int i = 0; i < n; i++) {
            xCoordinates.add(bottomLeft[i][0]);
            xCoordinates.add(topRight[i][0]);
        }

        List<Integer> sortedX = new ArrayList<>(xCoordinates);
        Collections.sort(sortedX);

        // For all possible y-coordinates (potential bottom edges of squares)
        Set<Integer> yCoordinates = new HashSet<>();
        for (int i = 0; i < n; i++) {
            yCoordinates.add(bottomLeft[i][1]);
            yCoordinates.add(topRight[i][1]);
        }

        List<Integer> sortedY = new ArrayList<>(yCoordinates);
        Collections.sort(sortedY);

        // For each potential square starting position and size
        for (int xi = 0; xi < sortedX.size(); xi++) {
            for (int yi = 0; yi < sortedY.size(); yi++) {
                int xStart = sortedX.get(xi);
                int yStart = sortedY.get(yi);

                // Binary search for maximum square side length
                int left = 0, right = Math.min(
                    sortedX.get(sortedX.size() - 1) - xStart,
                    sortedY.get(sortedY.size() - 1) - yStart
                );

                while (left <= right) {
                    int side = left + (right - left) / 2;
                    int xEnd = xStart + side;
                    int yEnd = yStart + side;

                    // Count how many rectangles contain this square [xStart, xEnd] × [yStart, yEnd]
                    int count = 0;
                    for (int i = 0; i < n && count < 2; i++) {
                        if (bottomLeft[i][0] <= xStart &&
                            bottomLeft[i][1] <= yStart &&
                            topRight[i][0] >= xEnd &&
                            topRight[i][1] >= yEnd) {
                            count++;
                        }
                    }

                    if (count >= 2) {
                        // This square fits in at least 2 rectangles
                        maxArea = Math.max(maxArea, (long) side * side);
                        left = side + 1; // Try larger square
                    } else {
                        right = side - 1; // Try smaller square
                    }
                }
            }
        }

        return maxArea;
    }
}
