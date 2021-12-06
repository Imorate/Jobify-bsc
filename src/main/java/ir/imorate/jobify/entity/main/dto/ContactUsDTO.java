package ir.imorate.jobify.entity.main.dto;

import ir.imorate.jobify.entity.main.enums.ContactUsType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContactUsDTO {

    @NotBlank(message = "{cu.title.null}")
    @Size(min = 5, message = "{cu.title.size}")
    private String title;

    @NotNull(message = "{cu.body.null}")
    private ContactUsType contactUsType;

    @NotBlank(message = "{cu.type.null}")
    @Size(min = 10, message = "{cu.body.size}")
    private String body;

}
