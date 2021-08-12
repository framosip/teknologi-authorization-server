package br.com.teknologi.as.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Slf4j
@RequiredArgsConstructor
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder);*/
        auth.inMemoryAuthentication()
                        .withUser("fernando")
                                .password(this.passwordEncoder.encode("123"))
                                .roles("ADMIN");
        log.info("[Configuration] ===== Auth Users in memory configured =====");
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        log.info("[Configuration] ===== AuthenticationManager bean configured =====");
        return super.authenticationManager();
    }
}
