package ir.imorate.jobify.service.security.mail;

import ir.imorate.jobify.entity.security.User;

import javax.mail.MessagingException;

public interface ActivationMailService {

    void sendEmail(User user, String token) throws MessagingException;

}
