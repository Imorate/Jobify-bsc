package ir.imorate.jobify.entity.main.mapper;

import ir.imorate.jobify.entity.main.ContactUs;
import ir.imorate.jobify.entity.main.dto.ContactUsDTO;
import ir.imorate.jobify.entity.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ContactUsMapper {

    @Mapping(target = "id", ignore = true)
    ContactUs dtoToContactUS(ContactUsDTO contactUsDTO, User user);

}
