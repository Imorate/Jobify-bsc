package ir.imorate.jobify.exception.security.confirmationtoken;

public class ConfirmationTokenExpiredException extends RuntimeException {

    public ConfirmationTokenExpiredException(String message) {
        super(message);
    }

}
