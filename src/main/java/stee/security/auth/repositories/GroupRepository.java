package stee.security.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stee.security.auth.entities.SteeGroup;

public interface GroupRepository extends JpaRepository<SteeGroup, Long> {
    SteeGroup findByGroupName( String groupName );
}
