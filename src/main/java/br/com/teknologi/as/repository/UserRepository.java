package br.com.teknologi.as.repository;

import br.com.teknologi.as.model.User;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepository {

    public Optional<User> findByEmail(@NonNull String email) {
        return Optional.of(new User("fulano@gmail.com", "$2a$12$MEakEDpGr8qkmTDLQaL0H.fsm2w/h0MrFuwHfzczuQFaMCtRcXCDi"));
    }

}
