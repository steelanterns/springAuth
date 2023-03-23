package stee.security.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stee.security.auth.entities.SteeUser;

public interface SteeUserRepository extends JpaRepository<SteeUser, String> {
    SteeUser findByUsername( String username );
}
