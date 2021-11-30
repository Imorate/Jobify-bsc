package ir.imorate.jobify.service.security.Impl;

import ir.imorate.jobify.entity.security.ConfirmationToken;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import ir.imorate.jobify.repository.security.ConfirmationTokenRepository;
import ir.imorate.jobify.service.security.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void createToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public void generateToken(ConfirmationTokenType confirmationTokenType, int hours, User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder().confirmationTokenType(confirmationTokenType).token(token)
                .expiresAt(LocalDateTime.now().plusHours(hours)).user(user).build();
        createToken(confirmationToken);
    }

}
