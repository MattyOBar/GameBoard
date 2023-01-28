package exceptions;

/**
 * Exception to throw when a given gameId is not found in the database.
 */

public class GameNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6296844590107473093L;


    /**
     * Exception with no message or cause.
     */
    public GameNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message a descriptive message for this exception.
     */

    public GameNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public GameNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public GameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
