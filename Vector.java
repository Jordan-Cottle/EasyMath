
/**
 * Class holds the definition for a vector as well as various methods that can work on them. 
 *
 * @author Jordan
 * @version 1
 */
public class Vector
{
    public final Point start;
    public final Point end;
    public final double [] components;
    public final int dimension;
    public final double magnitude;
    
    /**
     * Constructs a new position vector from an array of components
     * 
     * @param components A dynamically passed array of doubles that for the components of the vector
     */
    public Vector(double... components){
        this.components = components;
        this.dimension = components.length;
        this.magnitude = norm();
        
        // create starting and ending points from origin and given components
        double[] origin = new double[this.components.length];
        this.start = new Point(origin);
        this.end = new Point(this.components);
    }
    
    /**
     * Constructs a new direction vector from two Points
     * 
     * @param start The starting point for the vector
     * @param end   The ending point for the vector
     */
    public Vector(Point start, Point end){
        
        
        if(start.dimension == end.dimension){
            this.dimension = start.dimension;
        }
        else{
            throw new RuntimeException("Two points that form a vector must be in the same dimension!");
        }
        this.start = start;
        this.end = end;
        
        this.components = new double[start.dimension];
        for(int i = 0; i < this.components.length; i++){
            this.components[i] = this.end.coordinates[i] - this.start.coordinates[i];
        }
        
        this.magnitude = norm();
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
        double [] newComponents = new double [this.dimension];
        
        for (int i = 0; i < newComponents.length; i++){
            newComponents[i] = this.components[i] + other.components[i];
        }
        
        return new Vector(newComponents);
    }
    
    public Vector add(Vector [] others){
        Vector result = new Vector(0,0,0);
        
        for (Vector other: others){
            result = result.add(other);
        }
        
        return result;
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
        double sum = 0;
        for(int i = 0; i < dimension; i++){
            sum += this.components[i] * other.components[i];
        }
        
        return sum;
    }
    
    private double angleRatio(Vector other){
        return (this.dotProduct(other)) / (this.magnitude * other.magnitude);
    }
    
    public double angle(Vector other){
        return Math.acos(angleRatio(other));
    }
    
    
    public double angleDregree(Vector other){
        return angle(other) * (180 / Math.PI);
    }
    
    public double scalarProjectionOnto(Vector other){
        return other.dotProduct(this) / other.magnitude;
    }
    
    public Vector vectorProjectionOnto(Vector other){
        return other.normalize().multiply(scalarProjectionOnto(other));
    }
    
    public Vector orthagonalProjectionOnto(Vector other){
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
            Vector vector = (Vector) obj;
            
            for(int i = 0; i < dimension; i++){
                if (components[i] != vector.components[i]){
                    return false;
                } // end if
            } // end for
            
            return true;
        } // end else{}
    } // end equals
} // end class
