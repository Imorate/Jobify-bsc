package ir.imorate.jobify.service.security.Impl;

import ir.imorate.jobify.entity.security.ConfirmationToken;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenExpiredException;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenNotFoundException;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenTypeMismatchException;
import ir.imorate.jobify.repository.security.ConfirmationTokenRepository;
import ir.imorate.jobify.service.security.ConfirmationTokenService;
import ir.imorate.jobify.service.security.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserService userService;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository, @Lazy UserService userService) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userService = userService;
    }

    @Override
    public void createToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public String generateToken(ConfirmationTokenType confirmationTokenType, int hours, User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder().confirmationTokenType(confirmationTokenType).token(token)
                .expiresAt(LocalDateTime.now().plusHours(hours)).user(user).build();
        createToken(confirmationToken);
        return token;
    }

    @Override
    @Transactional
    public void acceptToken(String token, ConfirmationTokenType confirmationTokenType) throws ConfirmationTokenNotFoundException, ConfirmationTokenExpiredException, ConfirmationTokenTypeMismatchException {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByTokenIgnoreCaseAndConfirmedAtIsNull(token);
        if (confirmationToken.isEmpty()) {
            throw new ConfirmationTokenNotFoundException("Token " + token + " not found");
        } else if (confirmationToken.get().getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ConfirmationTokenExpiredException("Token " + token + " expired");
        } else if (!confirmationToken.get().getConfirmationTokenType().equals(confirmationTokenType)) {
            throw new ConfirmationTokenTypeMismatchException("Token type mismatch");
        }
        confirmationToken.get().setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken.get());
        if (confirmationTokenType.equals(ConfirmationTokenType.ACTIVATION)) {
            userService.enableUser(confirmationToken.get().getUser());
        }
    }

    @Override
    public String generateURL(String token, ConfirmationTokenType confirmationTokenType) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/confirm";
        if (confirmationTokenType.equals(ConfirmationTokenType.DELETE_ACCOUNT) ||
                confirmationTokenType.equals(ConfirmationTokenType.EMAIL_CHANGE)) {
            url += "/account";
        }
        return url + "/" + token;
    }

    @Override
    public Optional<ConfirmationToken> findToken(User user, ConfirmationTokenType confirmationTokenType) {
        return confirmationTokenRepository.findByUserAndConfirmedAtIsNullAndConfirmationTokenTypeIs(user, confirmationTokenType);
    }

}
