package ir.imorate.jobify.entity.security.enums.converter;

import ir.imorate.jobify.entity.security.enums.Gender;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;

public class StringToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        return Arrays.stream(Gender.values()).filter(gender -> gender.getTitle().equals(source)).findFirst()
                .orElse(Gender.OTHER);
    }

}
