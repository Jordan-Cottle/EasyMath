package objects;

import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class PointTest.
 *
 * @author  Jordan Cottle
 * @version 2/9/2019
 */
public class PointTest
{
    private static final String LOG_HEADER = "[PointTest] ";
    private static void log(String message){
        System.out.println(LOG_HEADER + message);
    }
    private static final double DOUBLE_PRECISION = Math.pow(10, -10);
    
    @Test
    public void distanceBetweenTheSamePointShouldEqual0(){
        Point a = new Point(2,3);
        
        assertEquals(0, a.distance(a), DOUBLE_PRECISION);
    }
    
    @Test
    public void random2dDistanceTest(){
        long seed = System.currentTimeMillis();
        log("randomDistanceTest seed = " + seed);
        Random r = new Random();
        
        final double range = 25.0;
        
        final double ax = r.nextDouble() * range;
        final double ay = r.nextDouble() * range;
        final double bx = r.nextDouble() * range;
        final double by = r.nextDouble() * range;
        
        Point a = new Point(ax, ay);
        Point b = new Point(bx, by);
        
        double xDistance = Math.abs(ax - bx);
        double yDistance = Math.abs(ay - by);
        
        double hypotenuse = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance,2));
        
        assertEquals(hypotenuse, a.distance(b), DOUBLE_PRECISION);
    }
}
