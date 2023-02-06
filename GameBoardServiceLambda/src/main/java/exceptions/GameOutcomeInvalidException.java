package exceptions;


public class GameOutcomeInvalidException extends RuntimeException {


    private static final long serialVersionUID = -9158604875003846541L;

    /**
     * Exception with no message or cause.
     */
    public GameOutcomeInvalidException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message a descriptive message for this exception.
     */

    public GameOutcomeInvalidException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public GameOutcomeInvalidException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public GameOutcomeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

}
