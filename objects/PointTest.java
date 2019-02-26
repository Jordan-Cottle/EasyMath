package objects;

import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 * The test class PointTest.
 *
 * @author  Jordan Cottle
 * @version 2/9/2019
 */
public class PointTest
{
    private static final String LOG_HEADER = "[PointTest] ";
    private static final double DOUBLE_PRECISION = Math.pow(10, -10);
    
    private static void log(String message){
        System.out.println(LOG_HEADER + message);
    }
    
    private static Random r;
    
    @BeforeClass
    public static void setUpRandom(){
        long seed = System.currentTimeMillis();
        log("Testing seed: " + seed);
        r = new Random(seed);
    }
    
    @Test
    public void distanceBetweenTheSamePointShouldEqual0(){
        Point a = new Point(2,3);
        
        assertEquals(0, a.distance(a), DOUBLE_PRECISION);
    }
    
    @Test
    public void random2dDistanceTest(){
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
    
    @Test
    public void fixedPythagoreanTripleTests(){
        // for these special ratios of base/height for right triangles, the hypotenuse is an integer
        int[] bases = {3, 5, 7, 8};
        int[] heights = {4, 12, 24, 15};
        int[] hypotenuses = {5, 13, 25, 17};
        
        Point base;
        Point height;
        
        // the distance between the points should scale up linearly 
        for(int multiple = 1; multiple < 10; multiple++){
            // loop through and test all of the base/height pairs
            for(int i = 0; i < bases.length; i++){
                base = new Point(bases[i] * multiple, 0);
                height = new Point(0, heights[i] * multiple);
                
                assertEquals(hypotenuses[i] * multiple, base.distance(height), DOUBLE_PRECISION);
            }
        }
    }
    
    @Test
    public void linearMidPointTest(){ 
        int maxDelta = 100;
        
        int a = r.nextInt(maxDelta + 1) - maxDelta/2;
        int b = r.nextInt(maxDelta + 1) - maxDelta/2;
        
        Point start = new Point(a);
        Point end = new Point(b);
        
        assertEquals(new Point((a+b) / 2.0), start.midPoint(end));
    }
    
    @Test
    public void multiDimensionalMidPointTest(){
        int dimension = r.nextInt(10) + 1;
        
        Point origin = Point.origin(dimension);
        
        Point a = randomPoint(dimension, 100);
        
        double[] midPointCoords = new double[dimension];
        int index = 0;
        for(double coordinate: a.coordinates){
            midPointCoords[index++] = coordinate / 2;
        }
        
        assertEquals(new Point(midPointCoords), a.midPoint(origin));
    }
    
    @Test
    public void testEquals(){
        int dimension = r.nextInt(10) + 1;
        double maxDelta = (r.nextDouble() * 500) + 500;
        
        double[] coordinates = new double [dimension];
        
        for(int i = 0; i < dimension; i++){
            coordinates[i] = (r.nextDouble() - .5) * maxDelta;
        }
        
        Point a = new Point(coordinates);
        Point b = new Point(coordinates);
        Point c = a;
        Point d = randomPoint(dimension, maxDelta);
        Point e = randomPoint(dimension + 1, maxDelta);
        
        assertEquals(a, b);
        assertEquals(a, c);
        assertEquals(b, c);
        
        assertNotEquals(a, d);
        assertNotEquals(b, d);
        assertNotEquals(c, d);
        
        try{
            assertEquals(d, e); // should throw an exception
            fail("Exception not thrown!");
        }
        catch(IllegalArgumentException exception){
            log(exception.getMessage());
        }
    }
    
    private Point randomPoint(int dimension, double maxDelta){
        double[] coordinates = new double [dimension];
        
        for(int i = 0; i < dimension; i++){
            coordinates[i] = (r.nextDouble() - .5) * maxDelta;
        }
        
        return new Point(coordinates);
    }
}
