/**
 * Created by chrisgibb on 22/02/16.
 */
public class TestPoint {


    public static void main(String[] args) {
        Point p = new Point(0, 500);
        Point q = new Point(0, 32768);
        Point r = new Point(41, 450);

        Point p1 = new Point(1, 2);
        Point q1 = new Point(1, 4);
        Point q2 = new Point(1, 4);

        System.out.println( p1.slopeOrder().compare(q1, q2)) ;
        System.out.println( p.slopeOrder().compare(q, r)) ;

    }

}
