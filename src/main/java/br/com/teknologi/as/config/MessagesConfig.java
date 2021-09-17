package br.com.teknologi.as.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@Slf4j
public class MessagesConfig {

    @Bean
    public MessageSource httpErrorsMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource ();
        messageSource.setBasename("classpath:httperrors");
        messageSource.setDefaultEncoding("UTF-8");

        log.debug("[Configuration] ===== httpErrorsMessageSource bean configured =====");

        return messageSource;
    }

}
