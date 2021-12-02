package ir.imorate.jobify.service.security.mail;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import ir.imorate.jobify.exception.security.ProfileNotFoundException;
import ir.imorate.jobify.service.security.ConfirmationTokenService;
import ir.imorate.jobify.service.security.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
    private final Map<String, Object> context = new HashMap<>();
    private final ProfileService profileService;
    private final ConfirmationTokenService confirmationTokenService;
    private String userEmail;

    @Override
    public void sendActivationEmail(User user, String token) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        userEmail = user.getEmail();
        Profile profile = profileService.findProfile(user).orElseThrow(() ->
                new ProfileNotFoundException("User " + user.getUsername() + " profile not found"));
        String confirmationUrl = confirmationTokenService.generateURL(token, ConfirmationTokenType.ACTIVATION);
        context.put("profile", profile);
        context.put("confirmationUrl", confirmationUrl);
        makeMimeMessage(mimeMessage, user);
        mailSender.send(mimeMessage);
    }

    private void makeMimeMessage(MimeMessage mimeMessage, User user) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setSubject(generateSubject());
        mimeMessageHelper.setFrom("no-reply@jobify.ir");
        mimeMessageHelper.setTo(userEmail);
        mimeMessageHelper.setText(processTemplate(context), true);
        ClassPathResource logoResource = new ClassPathResource("static/logo.svg");
        mimeMessageHelper.addInline("logo.svg", logoResource, "image/svg+xml");
    }

    private String generateSubject() {
        String applicationName = messageSource.getMessage("app.name", null, Locale.getDefault());
        String action = messageSource.getMessage("mail.title.activation", null, Locale.getDefault());
        return applicationName + " - " + action;
    }

    private String processTemplate(Map<String, Object> templateContext) {
        Context context = new Context();
        templateContext.forEach(context::setVariable);
        return templateEngine.process("security/mail/activation", context);
    }

}
