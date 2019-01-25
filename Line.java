/**
 * Class to create and perform calculations with lines
 *
 * @author Jordan Cottle
 * @version 1/25/2019
 */
public class Line
{
    public final Point point;
    public final Vector direction;
    public final int dimension;
    
    /**
     * Creates a line from a point and a direction
     * 
     * @param point An arbitrary point on the line
     * @param direction A vector that is parallel to the line
     */
    public Line(Point point, Vector direction){
        if(point.dimension == direction.dimension){
            this.dimension = point.dimension;
        }
        else{
            throw new RuntimeException("The point and direction vector of a line must be in the same dimension!");
        }
        this.point = point;
        this.direction = direction;
    }
    
    /**
     * Gives the parametreic equations for the line
     * 
     * @return The parametric equations for the line as an array of Strings
     */
    public String[] parametricEquations(){
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
        
        String [] equations = new String[this.dimension];
        
        for(int i = 0; i < this.dimension; i++){
            if (this.direction.components[i] >= 0){
                equations[i] = String.format("%s = %.2f + %.2ft",
                            variables[i],
                            this.point.coordinates[i],
                            this.direction.components[i]);
            }
            else{
                equations[i] = String.format("%s = %.2f - %.2ft",
                            variables[i],
                            this.point.coordinates[i],
                            Math.abs(this.direction.components[i]));
            }
        }
        return equations;
    }
    
    
    /**
     * Constructs the vector equation of the line
     * 
     * @return The vector equation representation of the line
     */
    public String vectorEquation(){
        // set up standard basis names
        String [] basisVectors;
        if (this.dimension < 4){
            basisVectors = new String[] {"i", "j", "k"};
        }
        else{
            basisVectors = new String[this.dimension];
            for (int i = 0; i < this.dimension; i++){
                basisVectors[i] = "e" + i;
            }
        }
        
        //build vector equation
        StringBuilder str = new StringBuilder("r(t) =");
 
        for(int i = 0; i < this.dimension; i++){
            if (i != 0){
                str.append(" + (");
            }
            else{
                str.append(" (");
            }
            
            str.append(this.point.coordinates[i]);
            
            if (this.direction.components[i] >= 0){
                str.append(" + ");
                str.append(this.direction.components[i]);
            }
            else{
                str.append(" - ");
                str.append(Math.abs(this.direction.components[i]));
            }
            str.append("t)" + basisVectors[i]);
        }
     
        return str.toString();
    }
    
    /**
     * Constructs the symmetric equations of the line
     * 
     * @return The symmetric equation representation of the line as a String array
     */
    public String[] symmetricEquations(){
        // set up variable names for later
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
        
        // count how many equations we need
        int equationCount = 1;
        for(double directionComponent: this.direction.components){
            if (directionComponent == 0){
                equationCount += 1;
            }
        }
        
        String [] equations = new String[equationCount];
        int constantEquationIndex = 1;  // equations[0] is the main equation
        
        //construct main equation
        StringBuilder str = new StringBuilder();
        String tail = " = ";
        for(int i = 0; i < this.dimension; i++){
            if (i == this.dimension - 1){
                tail = "";
            }
            if(this.direction.components[i] != 0){
                if (this.point.coordinates[i] > 0){
                    str.append(String.format("(%s - %.2f) / %.2f%s",
                    variables[i],
                    this.point.coordinates[i],
                    this.direction.components[i],
                    tail));
                }
                else if (this.point.coordinates[i] == 0){
                    str.append(String.format("%s / %.2f%s",
                    variables[i],
                    this.direction.components[i],
                    tail));
                }
                else{  // starting point coordinate is negative
                    str.append(String.format("(%s + %.2f) / %.2f%s",
                    variables[i],
                    Math.abs(this.point.coordinates[i]),
                    this.direction.components[i],
                    tail));
                }  
            } // end non zero direction component condition
            
            else{  //direction component is 0
                // create new constant equation
                equations[constantEquationIndex++] = String.format("%s = %.2f",
                                                                    variables[i],
                                                                    this.point.coordinates[i]);
            }
        } // end loop through dimensions
        equations[0] = str.toString();
        
        return equations;
    }
    
    /**
     * Returns the line as represented by its vector equation
     */
    public String toString(){
        return this.vectorEquation();
    }
}
