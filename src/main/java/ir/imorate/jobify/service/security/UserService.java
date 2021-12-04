package ir.imorate.jobify.service.security;

import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.dto.SignupDTO;
import ir.imorate.jobify.entity.security.enums.RoleType;

import javax.mail.MessagingException;
import java.util.Optional;

public interface UserService {

    void signup(SignupDTO signupDTO, RoleType roleType) throws MessagingException;

    Optional<User> findUser(String username);

    void enableUser(User user);

    boolean isUserExists(String username, String email);

}
