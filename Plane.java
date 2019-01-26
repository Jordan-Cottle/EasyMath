
/**
 * Creates a representation of a plane in Euclidean space
 *
 * @author Jordan Cottle
 * @version 1/25/2019
 */
public class Plane
{

    public final Point point;
    public final Vector norm;
    public final int dimension;

    /**
     * Constructs a new Plane from a point on the plane and an orthogonal Vector
     * 
     * @param point A point on the plane
     * @param vector A vector that is orthogonal to the plane
     */
    public Plane(Point point, Vector vector){
        if (point.dimension == vector.dimension){
            this.dimension = point.dimension;
        }
        else{
            throw new RuntimeException("Point and normal vector describing a plane must be in the same dimension!");
        }
        this.point = point;
        this.norm = vector;
    }

    /**
     * Construct a new Plane from three unique points
     * 
     * @param p A point on the plane (must be unique)
     * @param q A point on the plane (must be unique)
     * @param r A point on the plane (must be unique)
     */
    public Plane(Point p, Point q, Point r){
        if (p.dimension == q.dimension && p.dimension == r.dimension){
            this.dimension = p.dimension;
        }
        else{
            throw new RuntimeException("All points that define a plane must be in the same dimension!");
        }

        if(p.equals(q) || p.equals(r) || q.equals(r)){
            throw new RuntimeException("You need three unique points to define a plane!");
        }

        Vector pq = new Vector(p,q);
        Vector pr = new Vector(p,r);

        this.point = p;
        this.norm = pq.crossProduct(pr);
    }

    /**
     * Calculates the angle between two planes
     * 
     * @param other The other plane to compare with
     * 
     * @return The angle between two planes in radians
     */
    public double angle(Plane other){
        return this.norm.angle(other.norm);
    }

    /**
     * Calculates the angle between a plane and a vector
     * 
     * @param other The vector to compare with
     * 
     * @return The angle between the plane and vector in radians
     */
    public double angle(Vector other){
        return this.norm.angle(other);
    }

    /**
     * Constructs a linear equation representation of the plane
     * 
     * @return The equation of the plane in the form of a linear equation
     */
    public String linearEquation(){
        String [] variables;
        if (this.dimension < 4){
            variables = new String[] {"x", "y", "z"};
        }
        else{
            variables = new String[this.dimension];
            for (int i = 0; i < this.dimension; i++){
                variables[i] = "x" + i;
            }
        }

        double d = 0;
        boolean firstItem = true;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i<this.dimension; i++){
            double component = this.norm.components[i];
            d += component * this.point.coordinates[i];

            if(!firstItem){ // don't add +/- signs to first item in the equation
                if(this.norm.components[i] > 0){
                    str.append(" + ");
                }
                else if (this.norm.components[i] < 0){
                    str.append(" - ");
                }
            }

            if (component > 0 || (firstItem && component != 0)){
                str.append(String.format("%.2f%s",component, variables[i]));
                firstItem = false;
            }
            else if (component < 0){
                str.append(String.format("%.2f%s",Math.abs(component), variables[i]));
                firstItem = false;
            } 
        } // end dimension loop

        str.append(String.format(" = %.2f", d));

        return str.toString();
    }
}
