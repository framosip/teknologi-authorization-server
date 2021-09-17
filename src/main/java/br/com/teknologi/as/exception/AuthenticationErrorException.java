package br.com.teknologi.as.exception;

import br.com.teknologi.as.converter.jackson.AuthenticationErrorExceptionSerializer;
import br.com.teknologi.as.model.ErrorMessage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = AuthenticationErrorExceptionSerializer.class)
@Getter
public class AuthenticationErrorException extends OAuth2Exception {

    private final ErrorMessage errorMessage;

    public AuthenticationErrorException(ErrorMessage errorMessage) {
        super(errorMessage.getDescription());
        this.errorMessage = errorMessage;
    }

}
