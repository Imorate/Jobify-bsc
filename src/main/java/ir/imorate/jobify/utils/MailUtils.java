package ir.imorate.jobify.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

public final class MailUtils {

    public static String processTemplate(TemplateEngine templateEngine, String templateName, Map<String, Object> templateContext) {
        Context context = new Context();
        templateContext.forEach(context::setVariable);
        return templateEngine.process(templateName, context);
    }

    public static String makeSubject(String appName, String title) {
        return appName + " - " + title;
    }

    public static void makeMimeMessage(MimeMessage mimeMessage, String email, String subject, String text) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setFrom("no-reply@jobify.ir");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText(text, true);
        ClassPathResource logoResource = new ClassPathResource("static/logo.svg");
        mimeMessageHelper.addInline("logo.svg", logoResource, "image/svg+xml");
    }

}
