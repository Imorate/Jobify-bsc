package ir.imorate.jobify.service.security.mail;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import ir.imorate.jobify.exception.security.ProfileNotFoundException;
import ir.imorate.jobify.service.security.ConfirmationTokenService;
import ir.imorate.jobify.service.security.ProfileService;
import ir.imorate.jobify.utils.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActivationMailServiceImpl implements ActivationMailService {

    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private final TemplateEngine templateEngine;
    private final ProfileService profileService;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public void sendEmail(User user, String token) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        Profile profile = profileService.findProfile(user).orElseThrow(() ->
                new ProfileNotFoundException("User " + user.getUsername() + " profile not found"));
        String confirmationUrl = confirmationTokenService.generateURL(token, ConfirmationTokenType.ACTIVATION);
        Map<String, Object> context = new HashMap<>();
        context.put("profile", profile);
        context.put("confirmationUrl", confirmationUrl);
        String subject = MailUtils.makeSubject(messageSource.getMessage("app.name", null, Locale.getDefault()),
                messageSource.getMessage("mail.title.activation", null, Locale.getDefault()));
        String text = MailUtils.processTemplate(templateEngine, "security/mail/activation", context);
        MailUtils.makeMimeMessage(mimeMessage, user.getEmail(), subject, text);
        mailSender.send(mimeMessage);
    }

}
