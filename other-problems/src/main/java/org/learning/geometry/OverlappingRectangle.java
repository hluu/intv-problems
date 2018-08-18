package org.learning.geometry;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Give two rectangles, determine the overlapping area, which is defined
 * as w * h.
 *
 * Each rectangle is given by two coordinates -
 *  lower left (x,y) and upper right (x,y)
 *
 * Approach:
 *  * Draw out the rectangle,
 *      * Examine the left side and right side of rectangles
 *      * Examine the lower and upper line of rectangles
 *
 *  * Determine the width = min(r1.upperright.x r2.upperright.x) -
 *                          max(r1.lowerleft.x, r2.lowerleft.x)
 *
 */
public class OverlappingRectangle {
    public static void main(String[] args) {
        System.out.println(OverlappingRectangle.class.getName());


        Rectangle r1 = new Rectangle(2,1,5,5);
        Rectangle r2 = new Rectangle(3,2,5,7);

        test(r1, r2, 6);
        test(r2, r1, 6);

        Rectangle r3 = new Rectangle(2,1,5,5);
        Rectangle r4 = new Rectangle(6,1,8,7);

        test(r3, r4 , -1);
        test(r4, r1 , -1);
    }

    private static void test(Rectangle r1, Rectangle r2, int expectedArea) {
        System.out.printf("%ntest: r1=> %s, r2 => %s%n", r1, r2);

        int actual = rectOverlappedArea(r1, r2);

        System.out.printf("expected: %d, actual: %d%n", expectedArea, actual);

    }

    private static int rectOverlappedArea(Rectangle r1, Rectangle r2) {
        int w = Math.min(r1.upperRight.x, r2.upperRight.x) -
                Math.max(r1.lowerLeft.x, r2.lowerLeft.x);

        int h = Math.min(r1.upperRight.y, r2.upperRight.y) -
                Math.max(r1.lowerLeft.y, r2.lowerLeft.y);

        boolean overlapped = (w < 0) || (h < 0);

        if (overlapped) {
            return -1;
        } else {
            return w * h;
        }


    }

    private static class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Coord asCoord(int x, int y) {
            return new Coord(x,y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String toString() {
            return String.format("(%d,%d)", x, y);
        }

    }

    private static class Rectangle {
        private Coord lowerLeft;
        private Coord upperRight;

        public Rectangle(int lowerLeftX, int lowerLeftY, int upperRightX, int upperRightY) {
            lowerLeft = Coord.asCoord(lowerLeftX, lowerLeftY);
            upperRight = Coord.asCoord(upperRightX, upperRightY);
        }

        public Coord getLowerLeft() {
            return lowerLeft;
        }

        public Coord getUpperRight() {
            return upperRight;
        }

        public String toString() {
            return String.format("lowerLeft: %s, upperRight: %s", lowerLeft,upperRight);
        }
    }
}
