package br.com.teknologi.as.service;

import br.com.teknologi.as.model.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    @Resource(name = "httpErrorsMessageSource")
    private final MessageSource messageSource;

    public ErrorMessage getMessage(String key){
        Locale locale = LocaleContextHolder.getLocale();
        String message = this.messageSource.getMessage(key, null, locale);
        return new ErrorMessage(key, message);
    }

    public ErrorMessage getMessage(String key, String message){
        return new ErrorMessage(key, message);
    }
}
