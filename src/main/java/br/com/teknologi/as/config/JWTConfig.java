package br.com.teknologi.as.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@Slf4j
public class JWTConfig {

    @Value("security.key")
    private String signingKey;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        log.debug("[Configuration] ===== BCryptPasswordEncoder bean configured =====");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){

        log.debug("[Configuration] ===== JwtAccessTokenConverter configured =====");

        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(signingKey);

        return tokenConverter;
    }
}
