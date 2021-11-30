package ir.imorate.jobify.entity.security.dto;

import ir.imorate.jobify.entity.security.enums.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignupDTO {

    @NotBlank(message = "{sec.validation.username.null}")
    @Size(min = 3, max = 32, message = "{sec.validation.username.size}")
    private String username;

    @NotBlank(message = "{sec.validation.password.null}")
    private String password;

    @NotBlank(message = "{sec.validation.email.null}")
    @Email(message = "{sec.validation.email.valid}")
    @Size(min = 6, max = 32, message = "{sec.validation.email.size}")
    private String email;

    @NotBlank(message = "{sec.validation.first-name.null}")
    private String firstName;

    @NotBlank(message = "{sec.validation.last-name.null}")
    private String lastName;

    @NotNull(message = "{sec.validation.gender.valid}")
    private Gender gender;

    private String phoneNumber;

}
