package ir.imorate.jobify.repository.security;

import ir.imorate.jobify.entity.security.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

}