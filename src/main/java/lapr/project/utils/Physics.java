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
     * Calculates the quadratic formula with the values a, b and c and returns both values stored in an array
     * @param a the first value
     * @param b the second value
     * @param c the third value
     * @return an array storing the values given by the operation of the quadratic formula
     */
    public static double[] calculateQuadraticFormula(double a, double b, double c) {
        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (discriminant < 0) {
            return null;
        }
        
        double value1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double value2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        return new double[]{value1, value2};
    }

}
