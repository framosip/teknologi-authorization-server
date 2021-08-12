package br.com.teknologi.as.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@Slf4j
public class JWTConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        log.info("[Configuration] ===== BCryptPasswordEncoder bean configured =====");
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        log.info("[Configuration] ===== JwtAccessTokenConverter configured =====");
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();

        return tokenConverter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        log.info("[Configuration] ===== JwtTokenStore configured =====");
        return new JwtTokenStore(this.accessTokenConverter());
    }*/
}
