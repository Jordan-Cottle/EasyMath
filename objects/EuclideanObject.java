package objects;


/**
 * Abstract class EuclideanObject - Contains fields and methods common to points/vectors/lines/planes
 *
 * @author Jordan
 * @version 1/26/2019
 */
public abstract class EuclideanObject
{
    public final int dimension;

    /**
     * Constructor to assign dimension value from a given int
     * 
     * @param dimension The dimension that this Euclidean object lives in
     */
    public EuclideanObject(int dimension){
        this.dimension = dimension;
    }

    /**
     * Constructor to determine dimension of a new EuclideanObject from a set of other EuclideanObjects
     * 
     * @param objects A dynamic array of EuclideanObjects that this one will be based off of
     */ 
    public EuclideanObject(EuclideanObject... objects){
        if(objects.length == 1){
            this.dimension = objects[0].dimension;
        }
        else{  //check each object for the same dimension
            int excpectedDimension = objects[0].dimension;
            for(EuclideanObject object: objects){
                if(object.dimension != excpectedDimension){
                    throw new RuntimeException("Euclidean objects must be in the same dimension to be used in conjunction with each other!");
                }
            }
            this.dimension = excpectedDimension;
        }
    }

    /**
     * Checks if two EuclideanObjects exist in the same dimension and throws an exception if they are not
     */
    protected void checkDimension(EuclideanObject other){
        if(this.dimension != other.dimension){
            throw new RuntimeException("Two objects must be in the same dimension to perform calculations!");
        }
    }
}
