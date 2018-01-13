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
     * Calculates the correspondent value of the valueX given by parameter using the linear interpolation,
     * according to the intervals X and Y
     * @param initialX the initial value of the interval X
     * @param finalX the final value of the interval X
     * @param initialY the initial value of the interval Y
     * @param finalY the final value of the interval Y
     * @param valueX the value correspondent to the interval X
     * @return the correspondent value in the interval Y
     */
    public static double calculateLinearInterpolation(double initialX, double finalX, double initialY, double finalY,
                                                      double valueX) {
        return initialY + (finalY - initialY) * (valueX - initialX) / (finalX - initialX);
    }

    /**
     * Calculates the correspondent value of the valueX given by parameter using the polynomial interpolation,
     * according to the intervals X and Y
     * @param initialX the initial value of the interval X
     * @param finalX the final value of the interval X
     * @param initialY the initial value of the interval Y
     * @param finalY the final value of the interval Y
     * @param valueX the value correspondent to the interval X
     * @return the correspondent value in the interval Y
     */
    public static double calculatePolynomialInterpolation(double initialX, double finalX, double initialY, double finalY,
                                                          double valueX) {
        double x2 = (finalY - initialY) / (finalX - initialX);
        double x1 = initialY - (initialX * x2);
        return (x2 * valueX) + x1;
    }

}
