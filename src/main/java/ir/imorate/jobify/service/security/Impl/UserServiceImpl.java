package ir.imorate.jobify.service.security.Impl;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.Role;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.dto.SignupDTO;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import ir.imorate.jobify.entity.security.enums.RoleType;
import ir.imorate.jobify.entity.security.mapper.SignupMapper;
import ir.imorate.jobify.repository.security.UserRepository;
import ir.imorate.jobify.service.security.ConfirmationTokenService;
import ir.imorate.jobify.service.security.ProfileService;
import ir.imorate.jobify.service.security.RoleService;
import ir.imorate.jobify.service.security.UserService;
import ir.imorate.jobify.service.security.mail.ActivationMailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final SignupMapper signupMapper;
    private final ActivationMailService activationMailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUser(username)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public void signup(SignupDTO signupDTO, RoleType roleType) throws MessagingException {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findRole(roleType));
        User user = signupMapper.dtoToUser(signupDTO);
        Profile profile = signupMapper.dtoToProfile(signupDTO);
        profile.setUser(user);
        user.setRoles(roleSet);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userRepository.save(user);
        profileService.create(profile);
        String token = confirmationTokenService.generateToken(ConfirmationTokenType.ACTIVATION, 24, user);
        activationMailService.sendActivationEmail(user, token);
    }

    @Override
    public Optional<User> findUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public boolean isUserExists(String username, String email) {
        return userRepository.existsByUsernameOrEmailAllIgnoreCase(username, email);
    }

    private UserDetails createSpringSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getType().getTitle()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked()
                , grantedAuthorities);
    }

}
