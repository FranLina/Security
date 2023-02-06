package com.llv.exament4.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.llv.exament4.services.UserService;

@Configuration
public class SecurityConfig {

    @Bean
    UserService myUserService() {
        return new UserService();
    };

    @Bean
    public UserDetailsService user() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}1234")
                .authorities("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin*1234")
                .authorities("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auProvider = new DaoAuthenticationProvider();

        auProvider.setUserDetailsService(user());
        auProvider.setPasswordEncoder(null);

        return auProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity htpp) throws Exception {
        htpp.authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        return htpp.build();
    }
}
