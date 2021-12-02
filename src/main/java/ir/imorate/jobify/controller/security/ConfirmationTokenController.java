package ir.imorate.jobify.controller.security;

import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenExpiredException;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenNotFoundException;
import ir.imorate.jobify.exception.security.confirmationtoken.ConfirmationTokenTypeMismatchException;
import ir.imorate.jobify.service.security.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/confirm")
public class ConfirmationTokenController {

    private ConfirmationTokenService confirmationTokenService;
    private MessageSource messageSource;

    @GetMapping
    public String defaultRoute() {
        return "redirect:/";
    }

    @GetMapping("/{token}")
    public String activationConfirm(@PathVariable("token") String token, Model model) {
        String error = null;
        try {
            confirmationTokenService.acceptToken(token, ConfirmationTokenType.ACTIVATION);
        } catch (ConfirmationTokenNotFoundException e) {
            error = "notfound";
        } catch (ConfirmationTokenExpiredException e) {
            error = "expired";
        } catch (ConfirmationTokenTypeMismatchException e) {
            error = "invalid";
        }
        if (error != null) {
            error = messageSource.getMessage("ct." + error, null, Locale.getDefault());
        }
        model.addAttribute("err", error);
        return "security/confirmation";
    }

}
