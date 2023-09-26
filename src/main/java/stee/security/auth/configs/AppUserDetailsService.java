package stee.security.auth.configs;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import stee.security.auth.entities.AppUser;
import stee.security.auth.repositories.AppUserRepository;
import stee.security.auth.services.AppUserService;
/**
 * Created by Steeve Jean Chilles on 09/22/2023
 */

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        AppUserDetails steeUserDetails = new AppUserDetails(user);
       // return new User( steeUserDetails.getUsername(), steeUserDetails.getPassword(), steeUserDetails.getAuthorities() );
        return steeUserDetails;

//        return user.map( new SteeUserDetails::new )
//                .orElseThrow( () -> new UsernameNotFoundException( "User not found") );
    }
}
