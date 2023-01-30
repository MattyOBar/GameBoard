package exceptions;

import dynamodb.models.GameOutcome;

public class GameOutcomeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 6810218611332192949L;

    /**
     * Exception with no message or cause.
     */
    public GameOutcomeNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message a descriptive message for this exception.
     */

    public GameOutcomeNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public GameOutcomeNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public GameOutcomeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
