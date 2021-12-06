package ir.imorate.jobify.service.main;

import ir.imorate.jobify.entity.main.dto.ContactUsDTO;
import ir.imorate.jobify.entity.security.User;

import javax.mail.MessagingException;

public interface ContactUsService {

    void createContactUs(ContactUsDTO contactUsDTO, User user) throws MessagingException;

}
