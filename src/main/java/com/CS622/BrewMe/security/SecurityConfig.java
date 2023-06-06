package com.CS622.BrewMe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("REVIEWER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/brewer").hasRole("BREWER")
                        .requestMatchers("/beerType").hasRole("REVIEWER")
                        .requestMatchers("/beerForm/*").hasRole("REVIEWER")
                        .requestMatchers(HttpMethod.POST, "/aleForm").hasRole("REVIEWER")
                        .requestMatchers(HttpMethod.POST, "/lagerForm").hasRole("REVIEWER")
                        .requestMatchers("/review/SelectBeer").hasRole("REVIEWER")
                        .requestMatchers("/review/form/*").hasRole("REVIEWER")
                        .requestMatchers(HttpMethod.POST, "/review/form").hasRole("REVIEWER")
                        .requestMatchers(HttpMethod.POST, "review/delete/*").hasRole("REVIEWER")
                        .requestMatchers("/uploadData").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/uploadData").hasRole("ADMIN")
                        .requestMatchers("/exportMenu").hasRole("ADMIN")
                        .requestMatchers("/exportData").hasRole("ADMIN")
                        .anyRequest().authenticated()
        )
                .formLogin(form ->
                        form.loginPage("/showLoginPage")
                                .loginProcessingUrl("/authenticateUser")
                                .permitAll()
                ).logout(LogoutConfigurer::permitAll
                );
        return http.build();
    }

}
