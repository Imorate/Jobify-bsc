package ir.imorate.jobify.config.mvc;

import ir.imorate.jobify.entity.security.enums.converter.StringToGenderConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToGenderConverter());
    }

}
