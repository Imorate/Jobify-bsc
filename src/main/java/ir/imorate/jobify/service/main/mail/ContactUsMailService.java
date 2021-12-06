package ir.imorate.jobify.service.main.mail;

import ir.imorate.jobify.entity.security.User;

import javax.mail.MessagingException;

public interface ContactUsMailService {

    void sendEmail(User user) throws MessagingException;

}
