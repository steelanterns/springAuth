package stee.security.auth.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stee.security.auth.entities.SteeGroup;
import stee.security.auth.entities.SteeUser;
import stee.security.auth.forms.GroupUserForm;
import stee.security.auth.services.UserService;

import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // this is how to Autorize
    public List<SteeUser> users(){
        return userService.users();
    }

    @PostMapping(path = "users")
    public SteeUser save(@RequestBody SteeUser steeUser){
        return userService.add(steeUser);
    }

    @PostMapping(path = "/roles")
    public SteeGroup saveGroup(@RequestBody SteeGroup steeGroup){
        return userService.addGroup(steeGroup);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody GroupUserForm groupUserForm){
        userService.addUserToGroup(groupUserForm.getUsername(), groupUserForm.getGroupName());
    }
}
