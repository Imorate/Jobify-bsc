package ir.imorate.jobify.service.security;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;

import java.util.Optional;

public interface ProfileService {

    void create(Profile profile);

    Optional<Profile> findProfile(User user);

    Optional<Profile> currentUserLoginProfile();

}
