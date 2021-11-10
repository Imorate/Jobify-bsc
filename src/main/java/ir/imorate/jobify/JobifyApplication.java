package ir.imorate.jobify;

import ir.imorate.jobify.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class JobifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobifyApplication.class, args);
    }

}
