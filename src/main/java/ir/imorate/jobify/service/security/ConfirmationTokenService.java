package ir.imorate.jobify.service.security;

import ir.imorate.jobify.entity.security.ConfirmationToken;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;

public interface ConfirmationTokenService {

    void createToken(ConfirmationToken confirmationToken);

    void generateToken(ConfirmationTokenType confirmationTokenType, int hours, User user);

}
