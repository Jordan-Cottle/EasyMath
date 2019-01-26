
/**
 * Class to respresent a point in n dimensions defined by n scalars
 *
 * @author Jordan
 * @version 1/25/2019
 */
public class Point{  
    public final int dimension;
    public final double [] coordinates;
    
    /**
     * Constructs a new point from a set of coordinates
     * 
     * @param coordinates The coordinates as a dynamic array or doubles
     */
    Point(double... coordinates){
        this.coordinates = coordinates;
        this.dimension = this.coordinates.length;
    }
    
    /**
     * Calculates the distance between two points
     * 
     * @param other The end point to calculate distance to
     * 
     * @return The distance between the two points
     */
    public double distance (Point other){
        double sum = 0;
        for(int i = 0; i < this.coordinates.length; i++){
            sum += Math.pow(other.coordinates[i] - this.coordinates[i], 2);
        }
        return Math.sqrt(sum);
    }
    
    /**
     * Calculates the midpoint between two points
     * 
     * @param other The endpint to calculate the midpoint from
     * 
     * @return A Point that lies halfway between the two points
     */
    public Point midPoint(Point other){
        if (this.dimension != other.dimension){
            throw new RuntimeException("Two points must be in the same dimensino to have a midpoint!");
        }
        
        double [] coordinates = new double[this.dimension];
        
        for(int i = 0; i < this.dimension; i++){
            coordinates[i] = (this.coordinates[i] + other.coordinates[i]) / 2;
        }
        
        return new Point(coordinates);
    }
    
    public static Point origin(int dimension){
        return new Point(new double[dimension]);
    }
}
