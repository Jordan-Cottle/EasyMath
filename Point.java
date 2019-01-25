
/**
 * Class to respresent a point in n dimensions defined by n scalars
 *
 * @author Jordan
 * @version 1/25/2019
 */
public class Point{   
    public final int dimension;
    public final double [] coordinates;
    Point(double... coordinates){
        this.coordinates = coordinates;
        this.dimension = this.coordinates.length;
    }
    
    public double distance (Point other){
        double sum = 0;
        for(int i = 0; i < this.coordinates.length; i++){
            sum += Math.pow(other.coordinates[i] - this.coordinates[i], 2);
        }
        return Math.sqrt(sum);
    }
}
