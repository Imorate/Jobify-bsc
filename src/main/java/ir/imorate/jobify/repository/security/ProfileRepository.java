package ir.imorate.jobify.repository.security;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findProfileByUser(User user);

}