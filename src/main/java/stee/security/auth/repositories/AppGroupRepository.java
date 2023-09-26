package stee.security.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stee.security.auth.entities.AppGroup;

public interface AppGroupRepository extends JpaRepository<AppGroup, Long> {
    AppGroup findByGroupName(String groupName );
}
