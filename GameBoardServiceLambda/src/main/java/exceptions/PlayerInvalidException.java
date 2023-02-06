package exceptions;

/**
 * Exception to throw when a given playerId is not found in the database.
 */

public class PlayerInvalidException extends RuntimeException {

    private static final long serialVersionUID = -7398192081266306023L;


    /**
     * Exception with no message or cause.
     */
    public PlayerInvalidException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message a descriptive message for this exception.
     */

    public PlayerInvalidException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public PlayerInvalidException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public PlayerInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
