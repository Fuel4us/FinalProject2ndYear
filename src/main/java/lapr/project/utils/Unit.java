package lapr.project.utils;

/**
 *
 * Provides various unit multiples
 */
public enum Unit {
    
    KILOGRAM("kg"), GRAM("g"), KILOMETERS_PER_HOUR("km/h"), KILOMETER("km"), METER("m"), MILES_PER_HOUR("mp/h"), METERS_PER_SECOND("m/s"),
    ROTATIONS_PER_MINUTE("rpm"), DEGREE("º");
    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

}
