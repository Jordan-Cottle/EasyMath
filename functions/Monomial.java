package functions;

import java.util.Map;
import java.util.LinkedHashMap;
/**
 * Represents a 
 *
 * @author Jordan Cottle
 * @version 2/8/2019
 */
public class Monomial{


    private double coefficient;
    private LinkedHashMap<String, Double> variables;

    private boolean negative;

    /**
     * Creates a simple Monomial of one variable from a coefficient and an exponent
     * 
     * @param coefficient The coefficient to multiply this mononmial by
     * @param exponent The exponent the variable in this monomial is raised to
     */
    public Monomial(double coefficient, double exponent){
        this.coefficient = coefficient;
        this.negative = coefficient < 0;

        this.variables = new LinkedHashMap<String, Double>();
        variables.put("x", exponent);
    }

    /**
     * Creates a new multivariable monomial with a coefficient and map of variables to their exponents
     * 
     * @param coefficient The coefficient multiplied to this monomial
     * @param variables A LinkedHashMap of variable names with their exponents as keys, provided in the desired substitution order (if given only a plain array of values)
     */
    public Monomial(double coefficient, LinkedHashMap<String, Double> variables){
        this.coefficient = coefficient;
        this.negative = coefficient < 0;

        this.variables = variables;
    }

    /**
     * Creates a new monomial from a coefficient and array of exponents to apply to variables, automatically assings unique variable names
     * 
     * @param coefficient The leading coefficient for the monomial
     * @param exponents A dynamic array of exponents to apply to the variables in this monomial
     */
    public Monomial(double coefficient, double... exponents){
        this.coefficient = coefficient;
        this.negative = coefficient < 0;

        int variableNumber = 0;
        this.variables = new LinkedHashMap<String, Double>();
        for(double exponent: exponents){
            variables.put(String.format("x%d", variableNumber++), exponent);
        }
    }

    /**
     * Returns whether or not the leading coefficient is negative for this monomial
     */
    public boolean isNegative(){
        return this.negative;
    }

    /**
     * Computes the value of the monomial given a real number for each variable in the expression
     * 
     * @param values A map that provides a value for each variable in the monomial
     * 
     * @return The numerical value of the monomial after the provided values are substituted into the expression
     */
    public double getValue(Map<String, Double> values){
        double result = 1;
        for(String variable: variables.keySet()){
            // verify that the passed in map of values contains a value for evry variable in this monomial
            if (!values.containsKey(variable)){
                throw new RuntimeException(String.format("In order to compute a value for %s, you must provide a value for %s!", this, variable));
            }

            //multiply result by the passed in value raised to the corresponding exponent specified by this monomial
            result *= Math.pow(values.get(variable),  variables.get(variable)); 
        }
        return result * coefficient;
    }

    /**
     * Computes the value of the monomial by substituting, from left to right, each variable in the expression with the corresponding values from the provided list
     * 
     * @param values A dynamic array of values to substitue in the expression, in the order you wish them to be substituted in
     * 
     * @return The numerical value of the monomial after the provided values are substituted into the expression
     */
    public double getValue(double... values){
        if(values.length != variables.size()){
            throw new RuntimeException(String.format("In order to calculate a value for %s, you must provide a value for every variable", this));
        }
        double result = 1;
        int index = 0;
        for(String variable: variables.keySet()){
            result *= Math.pow(values[index++], variables.get(variable)); 
        }
        
        return result * coefficient;
    }

    public Monomial add(Monomial other){
        // TODO implement adding monomials together if their variables and powers match
        return this;
    }

    public Monomial multiply(Monomial other){
        // TODO implement multiplying two monomials together to form a single monomial
        return this;
    }

    public String toString(){
        StringBuilder str = new StringBuilder(this.negative ? "-": "");

        str.append(Double.toString(Math.abs(coefficient)));
        for(String variable: variables.keySet()){
            str.append(String.format("%s^%s", variable, Double.toString(variables.get(variable))));
        }

        return str.toString();
    }
}