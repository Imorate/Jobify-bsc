package ir.imorate.jobify.entity.security.mapper;

import ir.imorate.jobify.entity.security.Profile;
import ir.imorate.jobify.entity.security.User;
import ir.imorate.jobify.entity.security.dto.SignupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SignupMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    User dtoToUser(SignupDTO signupDTO);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Profile dtoToProfile(SignupDTO signupDTO);

}
