package lapr.project.utils;

/**
 * Provides physics related utilities
 */
public class Physics {

    public static final Measurable GRAVITY_ACCELERATION = new Measurable(9.8, Unit.METERS_PER_SECOND_SQUARED);
    public static final Measurable AIR_DENSITY = new Measurable(1.225, Unit.KILOGRAMS_PER_CUBIC_METER);
    public static final double KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO = 3.6;
    public static final double KILOMETERS_METERS_CONVERSION_RATIO = 0.001;

    /**
     * Calculates the quadratic formula with the values a, b and c and returns one of the values that should
     * be returned (when the signal is '+' (boolean -> true) and when the signal is '-' (boolean -> false))
     * @param a the first value
     * @param b the second value
     * @param c the third value
     * @param plus the boolean that indicates whether the operation is '+' (true) or '-' (false)
     * @return the value given by the operation of the quadratic formula
     */
    public static double calculateQuadraticFormula(double a, double b, double c, boolean plus) {
        if (plus) {
            return (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        } else {
            return (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        }
    }

}
