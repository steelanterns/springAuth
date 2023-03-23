package stee.security.auth.configs;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import stee.security.auth.entities.SteeUser;
import stee.security.auth.repositories.SteeUserRepository;


@Service
public class SteeUserDetailsService implements UserDetailsService {
    private SteeUserRepository steeUserRepository;

    public SteeUserDetailsService(SteeUserRepository steeUserRepository) {
        this.steeUserRepository = steeUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SteeUser user = steeUserRepository.findByUsername(username);

        SteeUserDetails steeUserDetails = new SteeUserDetails(user);
       // return new User( steeUserDetails.getUsername(), steeUserDetails.getPassword(), steeUserDetails.getAuthorities() );
        return steeUserDetails;

//        return user.map( new SteeUserDetails::new )
//                .orElseThrow( () -> new UsernameNotFoundException( "User not found") );
    }
}
