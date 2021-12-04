package ir.imorate.jobify.controller.main;

import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.service.security.ProfileService;
import ir.imorate.jobify.service.security.UserService;
import ir.imorate.jobify.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/")
    public String index(Model model) {
        checkAuthentication(model);
        return "main/index";
    }

    @GetMapping("/about-us")
    public String aboutUs(Model model) {
        checkAuthentication(model);
        return "main/about-us";
    }

    @GetMapping("/contact-us")
    public String contactUs(Model model) {
        checkAuthentication(model);
        return "main/contact-us";
    }

    private void checkAuthentication(Model model) {
        if (SecurityUtils.isAuthenticated()) {
            Optional<User> currentUser = SecurityUtils.getCurrentUserLogin().flatMap(userService::findUser);
            assert currentUser.isPresent();
            model.addAttribute("currentUser", currentUser);
            currentUser.flatMap(profileService::findProfile).ifPresent(profile -> {
                model.addAttribute("currentUserProfile", profile);
            });
        }
    }

}
