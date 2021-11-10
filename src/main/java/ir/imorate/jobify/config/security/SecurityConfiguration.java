package ir.imorate.jobify.config.security;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", environment.getProperty("app.url.signup")).permitAll()
                .antMatchers(environment.getProperty("app.url.login.url")).permitAll()
                .anyRequest()
                .authenticated();
    }
}
