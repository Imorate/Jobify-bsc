package ir.imorate.jobify.exception.security.confirmationtoken;

import javassist.NotFoundException;

public class ConfirmationTokenNotFoundException extends NotFoundException {

    public ConfirmationTokenNotFoundException(String msg) {
        super(msg);
    }

}
