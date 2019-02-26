package objects;


/**
 * Class to respresent a point in n dimensions defined by n scalars
 *
 * @author Jordan
 * @version 2/25/2019
 */
public class Point extends EuclideanObject{  
    public final double [] coordinates;
    
    /**
     * Constructs a new point from a set of coordinates
     * 
     * @param coordinates The coordinates as a dynamic array or doubles
     */
    Point(double... coordinates){
        super(coordinates.length);
        this.coordinates = coordinates;
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
        checkDimension(other);
        
        double [] coordinates = new double[this.dimension];
        
        for(int i = 0; i < this.dimension; i++){
            coordinates[i] = (this.coordinates[i] + other.coordinates[i]) / 2;
        }
        
        return new Point(coordinates);
    }
    
    public static Point origin(int dimension){
        return new Point(new double[dimension]);
    }
    
    
    public String toString(){
        StringBuilder str = new StringBuilder("Point (");
        for(double coordinate: coordinates){
            str.append(String.format("%.2f, ", coordinate));
        }
        
        str.replace(str.length()-2, str.length()-1, ")");
        return str.toString();
    }
    
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        else if(this.getClass() != other.getClass()){
            return false;
        }
        
        Point point = (Point) other;
        
        checkDimension(point);
        
        for(int i = 0; i < dimension; i++){
            double delta = Math.abs(this.coordinates[i] - point.coordinates[i]);
            if(delta > Math.pow(10, -10)){
                return false;
            }
        }
        
        return true;
    }
}
