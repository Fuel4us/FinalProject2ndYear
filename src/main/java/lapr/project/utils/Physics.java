package lapr.project.utils;

/**
 * Provides physics related utilities
 */
public class Physics {

    public static final Measurable GRAVITY_ACCELERATION = new Measurable(9.8, Unit.METERS_PER_SECOND_SQUARED);
    public static final Measurable AIR_DENSITY = new Measurable(1.225, Unit.KILOGRAMS_PER_CUBIC_METER);
    public static final double KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO = 3.6;
    public static final double KILOMETERS_METERS_CONVERSION_RATIO = 0.001;

}
