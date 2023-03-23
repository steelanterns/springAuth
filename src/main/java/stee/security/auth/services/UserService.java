package stee.security.auth.services;

import stee.security.auth.entities.SteeGroup;
import stee.security.auth.entities.SteeUser;

import java.util.List;

public interface UserService {
    SteeUser add(SteeUser user );
    SteeGroup addGroup (SteeGroup group );
    void addUserToGroup( String username, String name );
    SteeUser loadUserBayUsername( String username );
    List<SteeUser> users();
}
