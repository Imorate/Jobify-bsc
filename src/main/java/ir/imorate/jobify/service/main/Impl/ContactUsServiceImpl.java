package ir.imorate.jobify.service.main.Impl;

import ir.imorate.jobify.entity.main.ContactUs;
import ir.imorate.jobify.entity.main.dto.ContactUsDTO;
import ir.imorate.jobify.entity.main.mapper.ContactUsMapper;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.repository.main.ContactUsRepository;
import ir.imorate.jobify.service.main.ContactUsService;
import ir.imorate.jobify.service.main.mail.ContactUsMailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;
    private final ContactUsMapper contactUsMapper;
    private final ContactUsMailService contactUsMailService;

    @Override
    public void createContactUs(ContactUsDTO contactUsDTO, User user) throws MessagingException {
        ContactUs contactUs = contactUsMapper.dtoToContactUS(contactUsDTO, user);
        contactUsRepository.save(contactUs);
        contactUsMailService.sendEmail(user);
    }

}
