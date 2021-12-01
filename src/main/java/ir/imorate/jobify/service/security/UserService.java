package ir.imorate.jobify.service.security;

import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.dto.SignupDTO;
import ir.imorate.jobify.entity.security.enums.RoleType;

import java.util.Optional;

public interface UserService {

    void signup(SignupDTO signupDTO, RoleType roleType);

    Optional<User> findUser(String username);

    boolean isUserExists(String username, String email);

    boolean isAuthenticated();

    boolean hasCurrentUserAnyOfAuthorities(String... authorities);

    boolean hasCurrentUserNoneOfAuthorities(String... authorities);

    boolean hasCurrentUserThisAuthority(String authority);

    Optional<User> getCurrentUserLogin();

}
