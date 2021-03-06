/**
 * Class holds the definition for a vector as well as various methods that can work on them. 
 *
 * @author Jordan
 * @version 1/25/2019
 */
public class Vector extends EuclideanObject
{
    public final double [] components;
    public final double magnitude;
    
    public static Vector i(int dimension){
        if(dimension < 2) { 
            throw new RuntimeException("i is only defined for dimension 2 or higher");
        }
        double[] components = new double [dimension];
        components[0] = 1;
        return new Vector(components);
    }
    
    public static Vector j(int dimension){
        if(dimension < 2) { 
            throw new RuntimeException("j is only defined for dimension 2 or higher");
        }
        double[] components = new double [dimension];
        components[1] = 1;
        return new Vector(components);
    }
    
    public static Vector k(int dimension){
        if(dimension < 3) { 
            throw new RuntimeException("k is only defined for dimension 3 or higher");
        }
        double[] components = new double [dimension];
        components[2] = 1;
        return new Vector(components);
    }
    
    /**
     * Constructs a new position vector from an array of components
     * 
     * @param components A dynamically passed array of doubles that for the components of the vector
     */
    public Vector(double... components){
        super(components.length);
        this.components = components;
        this.magnitude = norm();
    }
    
    /**
     * Constructs a new direction vector from two Points
     * 
     * @param start The starting point for the vector
     * @param end   The ending point for the vector
     */
    public Vector(Point start, Point end){
        super(start, end);

        this.components = new double[start.dimension];
        for(int i = 0; i < this.components.length; i++){
            this.components[i] = end.coordinates[i] - start.coordinates[i];
        }
        
        this.magnitude = norm();
    }
    
    /**
     * Constructs a new position vector using the provided Point as the endpoint
     * 
     * @param endPoint The endpoint for the new position vector to describe
     */
    public Vector(Point endPoint){
        this(endPoint.coordinates);
    }

    private double norm() {
        double sum = 0;
        for (double component: this.components){
            sum += Math.pow(component, 2);
        }
        
        return Math.sqrt(sum);
    }
    
    public Vector invert(){
        double [] newComponents = new double [this.dimension];
        
        for (int i = 0; i < newComponents.length; i++){
            newComponents[i] = - this.components[i] ;
        }
        
        return new Vector(newComponents);
    }
    
    public Vector add(Vector other){
        checkDimension(other);
        double [] newComponents = new double [this.dimension];
        
        for (int i = 0; i < newComponents.length; i++){
            newComponents[i] = this.components[i] + other.components[i];
        }
        
        return new Vector(newComponents);
    }
    
    public Vector add(Vector... others){
        Vector result = new Vector(this.components);
        for (Vector other: others){
            checkDimension(other);
            result = result.add(other);
        }
        
        return result;
    }
    
    public Vector subtract(Vector other){
        checkDimension(other);
        return this.add(other.invert());
    }
    
    public Vector multiply(double scalar){
        double [] newComponents = new double[this.dimension];
        for(int i = 0; i < newComponents.length; i++){
            newComponents[i] = this.components[i] * scalar;
        }
        
        return new Vector(newComponents);
    }
    
    public Vector divide(double scalar){
        return this.multiply(1/scalar);
    }
    
    public Vector normalize(){
        return this.divide(magnitude);
    }
    
    public double dotProduct(Vector other){
        checkDimension(other);
        double sum = 0;
        for(int i = 0; i < dimension; i++){
            sum += this.components[i] * other.components[i];
        }
        
        return sum;
    }
    
    private double angleRatio(Vector other){
        checkDimension(other);
        return (this.dotProduct(other)) / (this.magnitude * other.magnitude);
    }
    
    public double angle(Vector other){
        checkDimension(other);
        return Math.acos(angleRatio(other));
    }
    
    
    public double angleDregree(Vector other){
        checkDimension(other);
        return angle(other) * (180 / Math.PI);
    }
    
    public double scalarProjectionOnto(Vector other){
        checkDimension(other);
        return other.dotProduct(this) / other.magnitude;
    }
    
    public Vector vectorProjectionOnto(Vector other){
        checkDimension(other);
        return other.normalize().multiply(scalarProjectionOnto(other));
    }
    
    public Vector orthagonalProjectionOnto(Vector other){
        checkDimension(other);
        return this.add(this.vectorProjectionOnto(other).invert());
    }
    
    public Vector crossProduct(Vector other){
        if (this.dimension != 3 || this.dimension != other.dimension){
            throw new RuntimeException("Both vectors must be 3 dimensional for the cross product to be defined");
        }
        return new Vector(
            (this.components[1] * other.components[2]) - (this.components[2] * other.components[1]),
            (this.components[2] * other.components[0]) - (this.components[0] * other.components[2]),
            (this.components[0] * other.components[1]) - (this.components[1] * other.components[0]));
    }
    
    
    public String toString(){
        StringBuilder str = new StringBuilder("<");
        
        for (double component: this.components){
            str.append(String.format("%.02f, ",component));
        }
        
        str.deleteCharAt(str.length()-2);
        str.setCharAt(str.length()-1, '>');
        
        return str.toString();
    } // end toString
    
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
        else if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        else{
            Vector other = (Vector) obj;
            
            if(this.dimension != other.dimension){
                return false;
            }
            
            for(int i = 0; i < dimension; i++){
                if (components[i] != other.components[i]){
                    return false;
                } // end if
            } // end for
            
            return true;
        } // end else{}
    } // end equals
} // end class
