package functions;


/**
 * Describes how other classes in the project will attempt to use equations.
 * An equation takes a series of real numbers as input and maps them to a single real number with a consistent pattern.
 *
 * @author Jordan Cottle
 * @version 2/8/2019
 */
public interface Equation
{
    /**
     * Returns the output of the equation as determined by the independent variables provided
     *
     * @param  independentVariables a dynamic array of values to use for each independent variable
     * @return   the result produced by the equation
     */
    double getValue(double... independentVariables);
    
    /**
     * Creates a string representation of an equation, each equation should be represented by its standard form
     */
    String toString();
}
