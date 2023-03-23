package stee.security.auth.configs;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import stee.security.auth.entities.SteeUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SteeUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities = new ArrayList<>();

    public SteeUserDetails(SteeUser steeUser) {
        this.username = steeUser.getUsername();
        this.password = steeUser.getPassword();
        this.authorities = mappedAuthorities(steeUser);
    }

    private Collection<GrantedAuthority> mappedAuthorities(SteeUser steeUser){
        Collection<GrantedAuthority> mappedAuthorities = new ArrayList<>();
        steeUser.getGroups().forEach( group -> {
            authorities.add( new SimpleGrantedAuthority( group.getGroupName() ) );
        });
        return mappedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
