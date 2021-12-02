package ir.imorate.jobify.service.security;

import ir.imorate.jobify.entity.security.ConfirmationToken;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenExpiredException;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenNotFoundException;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenTypeMismatchException;

import java.util.Optional;

public interface ConfirmationTokenService {

    void createToken(ConfirmationToken confirmationToken);

    String generateToken(ConfirmationTokenType confirmationTokenType, int hours, User user);

    void acceptToken(String token, ConfirmationTokenType confirmationTokenType) throws ConfirmationTokenNotFoundException, ConfirmationTokenExpiredException, ConfirmationTokenTypeMismatchException;

    String generateURL(String token, ConfirmationTokenType confirmationTokenType);

    Optional<ConfirmationToken> findToken(User user, ConfirmationTokenType confirmationTokenType);

}
