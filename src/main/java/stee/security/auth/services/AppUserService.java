package stee.security.auth.services;

import stee.security.auth.entities.AppGroup;
import stee.security.auth.entities.AppUser;

import java.util.List;

public interface AppUserService extends CrudService<AppUser, String> {
    AppGroup addGroup (AppGroup group );
    void addUserToGroup( String username, String name );
    void removeUserToGroup( String username, String name );
    AppUser loadUserByUsername( String username );
}
