import java.util.Arrays;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points

    private int sortedPointsCount = 0;
    private LineSegment[] segs;
    private final int MIN = 0;
    private final int MAX = 1;

    public BruteCollinearPoints(Point[] points) {
        // list of 5 x, y coordinates to store the
        Point[][] sortedPoints = new Point[5][2];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        if (areAnyNull(p, q, r, s)) {
                            throw new java.lang.NullPointerException();
                        }
                        if (isCollinear(p, q, r, s)) {
                            Point[] minMax = findMinMax(p, q, r, s);
                            Point min = minMax[0];
                            Point max = minMax[1];

                            addSegment(min, max, sortedPoints);
                        }
                    }
                }
            }
        }
        LineSegment[] segos = new LineSegment[sortedPointsCount];
        for (int i = 0; i < sortedPointsCount; i++) {
            segos[i] = new LineSegment(sortedPoints[i][MIN], sortedPoints[i][MAX]);
        }
        this.segs = segos;
        sortedPoints = null;
    }

    private void addSegment(Point min, Point max, Point[][] sortedPoints) {
        if (sortedPointsCount == 0) {
            sortedPoints[sortedPointsCount][MIN] = min;
            sortedPoints[sortedPointsCount][MAX] = max;
            sortedPointsCount++;
        } else {
            for (int i = 0; i < sortedPointsCount; i++) {
                int index = sortedPointsCount - 1;
                Point p1 = sortedPoints[i][MIN];
                Point p2 = sortedPoints[i][MAX];
                if (p1.slopeTo(p2) == min.slopeTo(max)) {
                    // slope is the same? same segment?
                    Point[] ar = new Point[]{p1, p2, min, max};
                    Arrays.sort(ar);
                    sortedPoints[i][MIN] = ar[0];
                    sortedPoints[i][MAX] = ar[3];
                } else {
                    // its a new line
                    sortedPoints[sortedPointsCount][MIN] = min;
                    sortedPoints[sortedPointsCount][MAX] = max;
                    sortedPointsCount++;
                }
            }
        }
    }

    private boolean isCollinear(Point p, Point q, Point r, Point s) {
        double slopepq = p.slopeTo(q);
        double slopeqr = q.slopeTo(r);
        double slopers = r.slopeTo(s);
        return (slopepq == slopeqr) && (slopeqr == slopers);
    }

    // the number of line segments
    public int numberOfSegments() {
        return sortedPointsCount;
    }

    private boolean areAnyNull(Point p, Point q, Point r, Point s) {
        return p == null || q == null || r == null || s == null;
    }

    // the line segments
    public LineSegment[] segments() {
        return segs;
    }


    private Point[] findMinMax(Point p, Point q, Point r, Point s) {
        Point[] segment = new Point[]{p, q, r, s};
        Arrays.sort(segment);
        return new Point[]{segment[0], segment[3]};
    }
}
