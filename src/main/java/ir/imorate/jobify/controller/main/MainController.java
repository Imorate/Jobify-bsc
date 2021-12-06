package ir.imorate.jobify.controller.main;

import ir.imorate.jobify.entity.main.dto.ContactUsDTO;
import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.service.main.ContactUsService;
import ir.imorate.jobify.service.security.ProfileService;
import ir.imorate.jobify.service.security.UserService;
import ir.imorate.jobify.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserService userService;
    private final ProfileService profileService;
    private final ContactUsService contactUsService;

    @GetMapping("/")
    public String index(Model model) {
        if (SecurityUtils.isAuthenticated()) {
            getCurrentLoginUser(model);
        }
        return "main/index";
    }

    @GetMapping("/about-us")
    public String aboutUs(Model model) {
        if (SecurityUtils.isAuthenticated()) {
            getCurrentLoginUser(model);
        }
        return "main/about-us";
    }

    @GetMapping("/contact-us")
    public String contactUs(Model model) {
        if (SecurityUtils.isAuthenticated()) {
            getCurrentLoginUser(model);
            model.addAttribute("contactUsDTO", new ContactUsDTO());
        }
        return "main/contact-us";
    }

    @PostMapping(value = "/contact-us")
    public String createContactUs(@Valid @ModelAttribute ContactUsDTO contactUsDTO, BindingResult bindingResult,
                                  Model model, RedirectAttributes redirectAttributes) throws MessagingException {
        User currentLoginUser = getCurrentLoginUser(model);
        if (bindingResult.hasErrors()) {
            return "main/contact-us";
        }
        contactUsService.createContactUs(contactUsDTO, currentLoginUser);
        redirectAttributes.addFlashAttribute("successMessage", true);
        return "redirect:/contact-us";
    }

    private User getCurrentLoginUser(Model model) {
        Optional<User> userOptional = userService.currentUserLogin();
        Optional<Profile> profileOptional = profileService.currentUserLoginProfile();
        if (userOptional.isPresent() && profileOptional.isPresent()) {
            model.addAttribute("currentUser", userOptional.get());
            model.addAttribute("currentUserProfile", profileOptional.get());
        }
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

}
