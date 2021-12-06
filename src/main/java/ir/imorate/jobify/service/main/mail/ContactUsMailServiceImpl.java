package ir.imorate.jobify.service.main.mail;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.exception.security.ProfileNotFoundException;
import ir.imorate.jobify.service.security.ProfileService;
import ir.imorate.jobify.utils.MailUtils;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ContactUsMailServiceImpl implements ContactUsMailService {

    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private final TemplateEngine templateEngine;
    private final ProfileService profileService;

    @Override
    public void sendEmail(User user) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        Profile profile = profileService.findProfile(user).orElseThrow(() ->
                new ProfileNotFoundException("User " + user.getUsername() + " profile not found"));
        Map<String, Object> context = new HashMap<>();
        context.put("profile", profile);
        String subject = MailUtils.makeSubject(messageSource.getMessage("app.name", null, Locale.getDefault()),
                messageSource.getMessage("mail.title.contactus", null, Locale.getDefault()));
        String text = MailUtils.processTemplate(templateEngine, "mail/contactus", context);
        MailUtils.makeMimeMessage(mimeMessage, user.getEmail(), subject, text);
        mailSender.send(mimeMessage);
    }

}
