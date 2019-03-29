package objects;

import java.util.Random;

/**
 * Handles fractions with integer valued numerator and denominator
 *
 * @author Jordan Cottle
 * @version 3/20/2019
 */
public class Fraction{
    private final int numerator;
    private final int denominator;

    /**
     * Creates a fraction with integer values for the numerator and denominator
     * 
     * @param numerator The numerator of the fraction
     * @param denominator The denominator of the fraction
     */
    public Fraction(int numerator, int denominator){
        if(denominator == 0){
            throw new IllegalArgumentException("Denominator of a Fraction cannot be 0!");
        }
        
        int [] values = simplify(numerator, denominator);
        
        this.numerator = values[0];
        this.denominator = values[1];
    }

    /**
     * Adds two fractions together
     * 
     * @param other The other fraction to add to this one
     * 
     * @return The result of adding the two fractions together
     */
    public Fraction add(Fraction other){
        int aTop = this.numerator * other.denominator;
        int bTop = other.numerator * this.denominator;

        int bottom = this.denominator * other.denominator;
        return new Fraction(aTop + bTop, bottom);
    }

    /**
     * Subtracts a fraction from this fraction
     * 
     * @param other The other fraction to use in subtraction
     * 
     * @return The result of subtracting the given fraction from this fraction
     */
    public Fraction subtract(Fraction other){
        return this.add(other.multiply(-1));
    }

    /**
     * Multiplies two fractions together
     * 
     * @param other The other fraction to multiply with this one
     * 
     * @return The result of multiplying two fractions together
     */
    public Fraction multiply(Fraction other){
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    /**
     * Multipilies a fraction by an integer value
     * 
     * @param multiple The value to multiply this fraction by
     * 
     * @return The result of multiplying this fraction by the given value
     */
    public Fraction multiply(int multiple){
        return new Fraction(numerator * multiple, denominator);
    }
    
    /**
     * Divides this fraction by the given fraction
     * 
     * @param other The fraction to divide this one by
     * 
     * @return The result of this Fraction divided by the other Fraction
     */
    public Fraction divide(Fraction other){
        return this.multiply(new Fraction(other.denominator, other.numerator));
    }
    
    /**
     * Determines if an object is equal to this fraction
     * 
     * @param o The other object to compare to this Fraction
     * 
     * @return Whether or not the other object was a Fraction with the same numerator and denominator as this Fraction
     */
    public boolean equals(Object o){
        if (this == o) return true;
        
        if (!(o instanceof Fraction)){
            return false;
        }
        
        Fraction other = (Fraction) o;
        
        // All fraction objects are simplified from their constructor
        return this.numerator == other.numerator && this.denominator == other.denominator;
    }
    
    /**
     * Compres two fractions by making their denominators the same and comparing the values of their numerators
     * 
     * @param other The other Fraction to compare to this one
     * 
     * @return A positive value if this Fraction is greater than the other Fraction, 0 if they are equal, and a negative value otherwise
     */
    public int compareTo(Fraction other){
        int numeratorThis = this.numerator * other.denominator;
        int numeratorOther = other.numerator * this.denominator;
        
        return numeratorThis - numeratorOther;
    }
    
    /**
     * Creates a String representation of this fraction
     * 
     * @return A string with format: numerator/denominator (ex: 3/5)
     */
    public String toString(){
        return String.format("%d/%d", numerator, denominator);
    }
    
    /**
     * Computes the decimal value of the fraction
     * 
     * @return The value of the numerator divided by the denominator
     */
    public double getDouble(){
        return (double) numerator / (double) denominator;
    }
    
    /**
     * Creates a ranom fraction with decimal balue betwen 0 and 1
     * 
     * @return A new random Fraction between 0 and 1
     */
    public static Fraction random(){
        Random r = new Random();
        
        int numerator = r.nextInt(100);
        int denominator;
        do{
            denominator = r.nextInt(numerator)+50;
        }while(numerator >= denominator);
        
        return new Fraction(numerator, denominator);
    }

    // Simplified the fraction's numerator/denominator if they are multiples of each other
    // returns int[] = {numerator, denominator}
    private int[] simplify(int numerator, int denominator){
        int gcf = greatestCommonFactor(numerator, denominator);

        numerator /= gcf;
        denominator /= gcf;

        return new int[] {numerator, denominator};
    }

    // Calculates the greatest common factor of two integers
    private int greatestCommonFactor(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);
        
        int smallerValue = a < b ? a: b;
        int largerValue = a > b ? a: b;

        int greatestCommonFactor = 1;
        for(int i = smallerValue; i > 0; i--){
            if(smallerValue % i != 0) continue;

            if(largerValue % i == 0){
                greatestCommonFactor = i;
                break;
            }
        }

        return greatestCommonFactor;
    }
}