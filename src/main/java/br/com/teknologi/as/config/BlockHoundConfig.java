package br.com.teknologi.as.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.blockhound.BlockHound;

@Configuration
@Slf4j
@Profile("local")
public class BlockHoundConfig {

    static {
        BlockHound.install();
        log.info("[Configuration] ===== Blockhound configured =====");
    }


}
