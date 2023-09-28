package stee.security.auth.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stee.security.auth.entities.AppUser;
import stee.security.auth.services.AppGroupService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class AppGroupServiceImpl  implements AppGroupService {

    @Override
    public List<AppUser> findAll() {
        return null;
    }

    @Override
    public Optional<AppUser> findById(String s) {
        return Optional.empty();
    }

    @Override
    public AppUser save(AppUser object) {
        return null;
    }

    @Override
    public void delete(AppUser object) {

    }

    @Override
    public void deleteById(String s) {

    }
}
