package lapr.project.utils;

/**
 *
 * Provides various unit multiples
 */
public enum Unit {
    
    KILOGRAM("kg"), GRAM("g"), KILOMETERS_PER_HOUR("km/h"), KILOMETER("km"), METER("m"), MILES_PER_HOUR("mp/h"), METERS_PER_SECOND("m/s"),
    ROTATIONS_PER_MINUTE("rpm"), DEGREE("º"), METERS_PER_SECOND_SQUARED("m/s^2"), METER_SQUARED("m^2"),
    KILOGRAMS_PER_CUBIC_METER("kg/m^3"), NEWTON_METER("Nm"), KILOJOULE_PER_GRAM("KJ/g");
    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

}
