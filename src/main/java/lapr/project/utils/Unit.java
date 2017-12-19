package lapr.project.utils;

/**
 *
 * Provides various unit multiples
 */
public enum Unit {
    
    KILOGRAM("kg"), GRAM("g"), KILOMETERSPERHOUR("km/h"), KILOMETER("km"), METER("m");
    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

}
