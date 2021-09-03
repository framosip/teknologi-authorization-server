package br.com.teknologi.as.repository;

import br.com.teknologi.as.model.User;
import lombok.NonNull;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(@NonNull String email);

}
