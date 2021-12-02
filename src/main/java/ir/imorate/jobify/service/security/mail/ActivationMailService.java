package ir.imorate.jobify.service.security.mail;

import ir.imorate.jobify.entity.security.User;

import javax.mail.MessagingException;

public interface ActivationMailService {

    void sendActivationEmail(User user, String token) throws MessagingException;

}
