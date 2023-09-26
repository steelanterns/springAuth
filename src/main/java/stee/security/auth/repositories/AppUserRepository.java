package stee.security.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stee.security.auth.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername( String username );
}
