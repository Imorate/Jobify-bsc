package ir.imorate.jobify.service.security.Impl;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.repository.security.ProfileRepository;
import ir.imorate.jobify.service.security.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public void create(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public Optional<Profile> findProfile(User user) {
        return profileRepository.findProfileByUser(user);
    }
}
