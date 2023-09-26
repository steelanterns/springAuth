package stee.security.auth.services.map;

import stee.security.auth.entities.AppGroup;
import stee.security.auth.services.CrudService;

import java.util.List;
import java.util.Optional;

public class AppGroupServiceMap extends AbstractMapService<AppGroup, Long> implements CrudService<AppGroup, Long> {
    @Override
    public List<AppGroup> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(AppGroup object) {
        super.delete( object );
    }

    @Override
    public AppGroup save(AppGroup object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Optional<AppGroup> findById(Long id) {
        return super.findById(id);
    }
}
