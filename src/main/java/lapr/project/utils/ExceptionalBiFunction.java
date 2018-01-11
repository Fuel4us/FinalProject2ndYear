package lapr.project.utils;

import java.util.function.BiFunction;

/**
 * Wraps a {@link BiFunction}, allowing for an {@link Exception} to be thrown
 */
@FunctionalInterface
public interface ExceptionalBiFunction<T, A, R> extends BiFunction<T, A, R> {

    /**
     * Applies this function to the given arguments.
     * If an {@link Exception} is thrown by the invoked method,
     * this method will relay it to whichever method invokes it,
     * allowing the logic of catching the exception to be delegated to the invoking method.
     * This {@link Override} allows compatibility with {@link BiFunction}
     * @param t the first function argument
     * @param a the second function argument
     * @return the function result
     */
    @Override
    default R apply(T t, A a) {
        try {
            return applyWithException(t, a);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Applies this function to the given arguments.
     * If an {@link Exception} is thrown by the invoked method,
     * this method will relay it to whichever method invokes it,
     * allowing the logic of catching the exception to be delegated to the invoking method.
     * @param t the first function argument
     * @param a the second function argument
     * @return the function result
     */
    R applyWithException(T t, A a) throws Exception;
}
