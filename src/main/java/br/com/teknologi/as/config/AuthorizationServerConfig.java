package br.com.teknologi.as.config;

import br.com.teknologi.as.constant.ErrorCode;
import br.com.teknologi.as.exception.AuthenticationErrorException;
import br.com.teknologi.as.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
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
    private final DictionaryService dictionaryService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("myappname123")
                .secret(this.passwordEncoder.encode("myappname123"))
                .scopes("read","write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(60 * 60)
                .refreshTokenValiditySeconds(60 * 60 * 2);

        log.debug("[Configuration] ===== Clients in memory configured =====");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.userDetailsService)
                .reuseRefreshTokens(false)
                .accessTokenConverter(this.jwtAccessTokenConverter)
                        .exceptionTranslator(exception -> {
                            if (exception instanceof OAuth2Exception) {
                                OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;

                                if(oAuth2Exception.getOAuth2ErrorCode().equals(OAuth2Exception.INVALID_GRANT)){
                                    return ResponseEntity
                                            .status(HttpStatus.BAD_REQUEST)
                                            .body(new AuthenticationErrorException(this.dictionaryService.getMessage(ErrorCode.BAD_REQUEST_INVALID_USER_OR_PASSWORD)));
                                }

                                return ResponseEntity
                                        .status(HttpStatus.BAD_REQUEST)
                                        .body(new AuthenticationErrorException(this.dictionaryService.getMessage(ErrorCode.BAD_REQUEST_GENERIC, oAuth2Exception.getMessage())));

                            } else {
                                throw exception;
                            }
                        });

        log.debug("[Configuration] ===== Endpoints authenticationManager configured =====");
    }

}
