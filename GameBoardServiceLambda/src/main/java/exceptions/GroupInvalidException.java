package exceptions;

public class GroupInvalidException extends RuntimeException {

    private static final long serialVersionUID = 5296855460104526671L;


    /**
     * Exception with no message or cause.
     */
    public GroupInvalidException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message a descriptive message for this exception.
     */

    public GroupInvalidException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public GroupInvalidException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public GroupInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

}
