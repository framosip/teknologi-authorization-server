package br.com.teknologi.as.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
@Slf4j
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("myappname123")
                .secret(this.passwordEncoder.encode("myappname123"))
                .scopes("read","write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(60 * 60)
                .refreshTokenValiditySeconds(60 * 60 * 2);


        log.info("[Configuration] ===== Clients in memory configured =====");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.userDetailsService)
                .reuseRefreshTokens(false)
                .accessTokenConverter(this.jwtAccessTokenConverter);

        log.info("[Configuration] ===== Endpoints authenticationManager configured =====");
    }
}
