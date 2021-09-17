package br.com.teknologi.as.converter.jackson;

import br.com.teknologi.as.exception.AuthenticationErrorException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AuthenticationErrorExceptionSerializer extends StdSerializer<AuthenticationErrorException> {


    protected AuthenticationErrorExceptionSerializer() {
        super(AuthenticationErrorException.class);
    }

    @Override
    public void serialize(AuthenticationErrorException ex, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("code", ex.getErrorMessage().getCode());
        jsonGenerator.writeStringField("description", ex.getErrorMessage().getDescription());
        jsonGenerator.writeEndObject();

    }
}
