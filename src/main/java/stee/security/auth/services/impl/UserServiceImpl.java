package stee.security.auth.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stee.security.auth.entities.SteeGroup;
import stee.security.auth.entities.SteeUser;
import stee.security.auth.repositories.GroupRepository;
import stee.security.auth.repositories.SteeUserRepository;
import stee.security.auth.services.UserService;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private SteeUserRepository steeUserRepository;
    private GroupRepository groupRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(SteeUserRepository steeUserRepository, GroupRepository groupRepository, PasswordEncoder passwordEncoder) {
        this.steeUserRepository = steeUserRepository;
        this.groupRepository = groupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SteeUser add(SteeUser user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setId(UUID.randomUUID().toString());
        return steeUserRepository.save(user);
    }

    @Override
    public SteeGroup addGroup(SteeGroup group) {
        return groupRepository.save(group);
    }

    @Override
    public void addUserToGroup(String username, String name) {
        SteeUser user = steeUserRepository.findByUsername(username);
        SteeGroup group = groupRepository.findByGroupName(name);

        user.getGroups().add( group );
    }

    @Override
    public SteeUser loadUserBayUsername(String username) {

        return steeUserRepository.findByUsername(username);
    }

    @Override
    public List<SteeUser> users() {
        return steeUserRepository.findAll();
    }
}
