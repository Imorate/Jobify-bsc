package ir.imorate.jobify.repository.security;

import ir.imorate.jobify.entity.security.ConfirmationToken;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByTokenAndConfirmedAtIsNull(@NonNull String token);

    Optional<ConfirmationToken> findByUserAndConfirmedAtIsNullAndConfirmationTokenTypeIs(User user, ConfirmationTokenType confirmationTokenType);

}