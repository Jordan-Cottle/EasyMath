package functions;

import objects.Vector;
import objects.Point;

/**
 * Function that takes a single variable as a paramter, and maps it to a Vector
 *
 * @author Jordan Cottle
 * @version 2/8/2019
 */
public class VectorFunction
{
    private Vector start;
    private Vector direction;

    /**
     * Constructs a linear Vector function where the rate of change is a constant vector
     * 
     * @param constant A constant position Vector that points to a point on the line that this function will draw out
     * @param change A vector that represents the slope of direction this function will draw towards
     */
    public VectorFunction(Vector constant, Vector change)
    {
        this.start = constant;
        this.direction = change;
    }
    
    /**
     * Constructs a Vector function where the rate of change is represented by a vector and a single independent variable
     * 
     * @param constant A point on the line that this function will draw out
     * @param change A vector that represents the slope of direction this function will draw towards
     */
    public VectorFunction(Point constant, Vector change)
    {
        this.start = new Vector(constant);
        this.direction = change;
    }
    
    public Vector getValue(double t){
        return start.add(direction.multiply(t));
    }
    
    public String toString(){
        return String.format("r(t) = %s + %st", start, direction);
    }

}
