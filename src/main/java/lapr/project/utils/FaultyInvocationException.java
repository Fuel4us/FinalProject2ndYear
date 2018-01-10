package lapr.project.utils;

//ToDo
public class FaultyInvocationException extends Throwable {

    private Object faultyObject;

    /**
     * Constructs a new throwable with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     * <p>
     * <p>The {@link #fillInStackTrace()} method is called to initialize
     * the stack trace data in the newly created throwable.
     */
    public FaultyInvocationException(Object faultyObject) {
        super();
        this.faultyObject = faultyObject;
    }

    /**
     * @return the Object that originated the exception
     */
    public Object getFaultyObject() {
        return faultyObject;
    }
}
