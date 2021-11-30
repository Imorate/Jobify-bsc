package ir.imorate.jobify.config.security;

import ir.imorate.jobify.config.properties.ControllerProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ControllerProperties controllerProperties;
    private final ControllerProperties.Login loginControllerProperties;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PersistentTokenRepository persistentTokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String loginUrl = loginControllerProperties.getUrl();
        http
                .authorizeRequests()
                .antMatchers("/", controllerProperties.getSignup()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(loginUrl).permitAll()
                .defaultSuccessUrl(controllerProperties.getDashboard()).permitAll()
                .failureUrl(loginControllerProperties.getFailure())
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .tokenRepository(persistentTokenRepository)
                .and()
                .logout()
                .logoutUrl(controllerProperties.getLogout())
                .logoutSuccessUrl("/")
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/favicon.ico", "/logo.svg", "/style/**", "/media/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

}
