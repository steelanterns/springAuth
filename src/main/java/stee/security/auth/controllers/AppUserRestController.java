package stee.security.auth.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stee.security.auth.entities.AppGroup;
import stee.security.auth.entities.AppUser;
import stee.security.auth.forms.AppGroupUserForm;
import stee.security.auth.services.AppUserService;

import java.util.List;
/**
 * Created by Steeve Jean Chilles on 09/22/2023
 */
@RestController
public class AppUserRestController {

    private AppUserService appUserService;

    public AppUserRestController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    //@CrossOrigin(origins = "http://localhost:8082")
    @GetMapping(path = "/users")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // this is how to Autorize
    public List<AppUser> users(){
        return appUserService.findAll();
    }

    @PostMapping(path = "users")
    public AppUser save(@RequestBody AppUser appUser){
        return appUserService.save( appUser );
    }

    @PostMapping(path = "/roles")
    public AppGroup saveGroup(@RequestBody AppGroup appGroup){
        return appUserService.addGroup( appGroup );
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody AppGroupUserForm appGroupUserForm){
        appUserService.addUserToGroup(appGroupUserForm.getUsername(), appGroupUserForm.getGroupName());
    }
}
