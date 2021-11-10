package ir.imorate.jobify.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.url")
@Getter
@Setter
public class ControllerProperties {

    /**
     * Signup url
     */
    private String signup;

    /**
     * Dashboard url
     */
    private String dashboard;

    /**
     * Logout url
     */
    private String logout;

    @ConfigurationProperties(prefix = "app.url.login")
    @Getter
    @Setter
    public static class Login {

        /**
         * Login url
         */
        private String url;

        /**
         * Failure url
         */
        private String failure;

    }

}
