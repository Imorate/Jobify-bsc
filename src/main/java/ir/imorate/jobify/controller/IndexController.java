package ir.imorate.jobify.controller;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.service.security.ProfileService;
import ir.imorate.jobify.service.security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class IndexController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/")
    public String index(Model model) {
        if (userService.isAuthenticated()) {
            Optional<User> currentUserLogin = userService.getCurrentUserLogin();
            if (currentUserLogin.isPresent()) {
                model.addAttribute("currentUser", currentUserLogin.get());
                Optional<Profile> currentUserProfile = profileService.findProfile(currentUserLogin.get());
                currentUserProfile.ifPresent(profile -> model.addAttribute("currentUserProfile", profile));
            }
        }
        return "index";
    }

}
