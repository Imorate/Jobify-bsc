package ir.imorate.jobify.repository.security;

import ir.imorate.jobify.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    boolean existsByUsernameOrEmailAllIgnoreCase(String username, String email);

}