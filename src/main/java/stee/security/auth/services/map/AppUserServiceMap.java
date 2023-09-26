package stee.security.auth.services.map;

import stee.security.auth.entities.AppUser;
import stee.security.auth.services.CrudService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AppUserServiceMap extends AbstractMapService<AppUser, String> implements CrudService<AppUser, String> {
    @Override
    public List<AppUser> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public void delete(AppUser object) {
        super.delete(object);
    }

    @Override
    public AppUser save(AppUser object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Optional<AppUser> findById(String id) {
        return super.findById(id);
    }
}
