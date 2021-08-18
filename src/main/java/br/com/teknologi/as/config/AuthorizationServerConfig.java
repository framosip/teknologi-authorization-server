package br.com.teknologi.as.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
@Slf4j
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAccessTokenConverter accessTokenConverter;
    /*private final JwtTokenStore tokenStore;*/
    private final AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security/*.tokenKeyAccess("permitAll()")*/
                .checkTokenAccess("isAuthenticated()");

        log.info("[Configuration] ===== AuthorizationServerSecurityConfigurer configured =====");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("myappname123")
                .secret(this.passwordEncoder.encode("myappsecret123"))
                .scopes("read","write")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(60 * 60);

        log.info("[Configuration] ===== Clients in memory configured =====");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager)
/*                .tokenStore(this.tokenStore)*/
                .accessTokenConverter(this.accessTokenConverter);

        log.info("[Configuration] ===== Endpoints authenticationManager configured =====");
    }
}
