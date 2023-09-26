package stee.security.auth.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stee.security.auth.entities.AppGroup;
import stee.security.auth.entities.AppUser;
import stee.security.auth.repositories.AppGroupRepository;
import stee.security.auth.repositories.AppUserRepository;
import stee.security.auth.services.AppUserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
/**
 * Created by Steeve Jean Chilles on 09/22/2023
 */
@Service
@Slf4j
@Transactional
public class AppUserServiceImpl implements AppUserService {
    private AppUserRepository appUserRepository;
    private AppGroupRepository appGroupRepository;
    private PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppGroupRepository appGroupRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appGroupRepository = appGroupRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public AppGroup addGroup(AppGroup group) {
        return appGroupRepository.save(group);
    }

    @Override
    public void addUserToGroup(String username, String name) {
        AppUser user = appUserRepository.findByUsername(username);
        AppGroup group = appGroupRepository.findByGroupName(name);

        user.getGroups().add( group );
        //appUserRepository.save( user ); not necessary thanks to the fact that the class is transactional
    }

    @Override
    public void removeUserToGroup(String username, String name) {
        AppUser user = appUserRepository.findByUsername(username);
        AppGroup group = appGroupRepository.findByGroupName(name);

        user.getGroups().remove( group );
    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(String s) {
        return appUserRepository.findById( s );
    }

    @Override
    public AppUser save(AppUser appUser) {
        String password = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setId(UUID.randomUUID().toString());
        return appUserRepository.save(appUser);
    }

    @Override
    public void delete(AppUser object) {

    }

    @Override
    public void deleteById(String s) {

    }
}
