package ir.imorate.jobify.controller.security;

import ir.imorate.jobify.entity.security.dto.SignupDTO;
import ir.imorate.jobify.entity.security.enums.RoleType;
import ir.imorate.jobify.service.security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class SecurityController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@ModelAttribute("registeredUser") SignupDTO registeredUser) {
        return "security/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupDTO", new SignupDTO());
        return "security/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupDTO signupDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (userService.isUserExists(signupDTO.getUsername(), signupDTO.getEmail())) {
            bindingResult.reject("sec.validation.user-exists.reject", "Registration Error");
            return "security/signup";
        }
        if (bindingResult.hasErrors()) {
            return "security/signup";
        }
        userService.signup(signupDTO, RoleType.ROLE_USER);
        redirectAttributes.addFlashAttribute("registeredUser", signupDTO);
        return "redirect:/login";
    }
}
