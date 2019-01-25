
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
    
    /**
     * Constructs a new Plane from a point on the plane and an orthogonal Vector
     */
    public Plane(Point point, Vector vector){
        this.point = point;
        this.norm = vector;
    }
}
