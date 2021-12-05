package ir.imorate.jobify.repository.security;

import ir.imorate.jobify.entity.security.ConfirmationToken;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByTokenIgnoreCaseAndConfirmedAtIsNull(String token);

    Optional<ConfirmationToken> findByUserAndConfirmedAtIsNullAndConfirmationTokenTypeIs(User user, ConfirmationTokenType confirmationTokenType);

}