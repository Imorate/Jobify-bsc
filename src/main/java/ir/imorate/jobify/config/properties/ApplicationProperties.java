package ir.imorate.jobify.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class ApplicationProperties {

    /**
     * Name of the application.
     */
    private String name = "Jobify";

    /**
     * Description of the application.
     */
    private String description;

    /**
     * Version of the application.
     */
    private String version;

}
