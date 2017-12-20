package lapr.project.utils;

/**
 * Defines behaviour for double unary operations applied on generic types
 */
@FunctionalInterface
public interface GeneralOperator<V> {

    /**
     * Defines operations with elements of the same type,
     * under the condition that the return must be a primitive double
     * @param v A parametrized type
     * @return A double
     */
    double apply(V v);

}
