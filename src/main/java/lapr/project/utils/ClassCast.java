package lapr.project.utils;

/**
 * Handles specific casts
 */
public class ClassCast {

    @SuppressWarnings("unchecked")
    public static <T> T uncheckedCast(Object o) {
        return (T) o;
    }

}


