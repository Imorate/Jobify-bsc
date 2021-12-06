package ir.imorate.jobify.entity.main.enums.converter;

import ir.imorate.jobify.entity.main.enums.ContactUsType;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;

public class StringToContactUsTypeConverter implements Converter<String, ContactUsType> {

    @Override
    public ContactUsType convert(String source) {
        return Arrays.stream(ContactUsType.values()).filter(type -> type.getTitle().equals(source)).findFirst()
                .orElse(ContactUsType.QUESTION);
    }

}
