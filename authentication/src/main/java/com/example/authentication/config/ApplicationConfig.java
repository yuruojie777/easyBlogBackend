package com.example.authentication.config;

import com.example.authentication.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {

    private final UserRepo userRepo;


    /**
     * UserDetailsService interface only has one method to be implemented, so to instantiate its object, we only
     * need to use lambda expression to tell it.
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    /**
     * The authentication provider is to help system authenticate username and password, DaoAuthenticationProvider
     * is an implementation of AbstractUserDetailsAuthenticationProvider. In this abstract class, it defines authenticate
     * method, and it will return an Authentication class
     * Set userDetailsService and passwordEncoder to the authentication provider(DaoAuthenticationProvider)
     * UserDetailsService tells it how to find the user details, PasswordEncoder tells it how we encoded the password.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    /**
     * AuthenticationManager is also an interface, for this method, it obtains AuthenticationManager from a
     * configuration, but where is the configuration coming from?
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
