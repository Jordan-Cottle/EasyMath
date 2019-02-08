package functions;

/**
 * Represents a polynomial equation of one variable
 *
 * @author Jordan Cottle
 * @version 2/8/2019
 */
public class Polynomial implements Equation
{
    private double[] coefficients;
    private int degree;

    /**
     * Creates a simple polynomial from an array of coefficents in descending order.
     * Place leading coefficient at index [0] and the polynomials constant (degree 0) as the last index.
     * Note: If your polynomial is missing a term (x^2 + 2 is missing a x^1 term) be sure to provide a 0 for its corresponding coefficient.
     * 
     * @param coefficients An array of coefficient to construct the polynomial with
     */
    public Polynomial(double... coefficients){
        this.coefficients = coefficients;

        this.degree = coefficients.length - 1;
    }

    /**
     * Performs the function operation on the given input
     * 
     * @param variableValues The value to put into the polynomial
     * 
     * @return The value of the polynomial at the given point
     */
    @Override
    public double getValue(double... variableValues){
        if(variableValues.length > 1){
            throw new RuntimeException("Use PolyPolynomial to work with multivariable Polynomials");
        }
        double x = variableValues[0];
        double sum = 0;
        int exponent = degree;
        for(int i = 0; i < coefficients.length; i++){
            sum += coefficients[i] * Math.pow(x, exponent--);
        }

        return sum;
    }

    /**
     * @return A string representation of the polynomial with each term in descending order
     */
    public String toString(){
        StringBuilder str = new StringBuilder("y = ");

        int exponent = degree;
        boolean firstTerm = true;
        for(int i = 0; i < coefficients.length; i++){
            double coefficient = coefficients[i];
            // skip empty terms
            if(coefficient == 0){
                exponent--;
                continue;
            }

            String expoStr;
            if(exponent > 1){
                expoStr = String.format("x^%d", exponent);
            }
            else if (exponent == 1){ // exponent == 1 or 0
                expoStr = "x";
            }
            else{
                expoStr = "";
            }
            
            exponent--;

            // handle formatting positive/negative numbers differently
            if(firstTerm){
                // don't display the coefficient if it is 1 (it is implied/understood to be there)
                if(coefficient == 1){
                    str.append(String.format("%s ", expoStr));
                }
                else if(coefficient == -1){
                    str.append(String.format("-%s ", expoStr));
                }else{
                    str.append(String.format("%.2f%s ", coefficient, expoStr));
                }
                firstTerm = false;
            } 
            else if(coefficient > 0){
                if(coefficient == 1){
                    str.append(String.format("+ %s ", expoStr));
                }else{
                    str.append(String.format("+ %.2f%s ", coefficient, expoStr));
                }
            } else{
                if(coefficient == -1){
                    str.append(String.format("- %s ", expoStr));
                }else{
                    str.append(String.format("- %.2f%s ", Math.abs(coefficient), expoStr));
                }
            }
        } // end formatting logic block
        return str.toString();
    } // end toString
}
