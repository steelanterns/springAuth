package stee.security.auth.configs;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import stee.security.auth.entities.AppUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities = new ArrayList<>();

    public AppUserDetails(AppUser appUser) {
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
        this.authorities = mappedAuthorities(appUser);
    }

    private Collection<GrantedAuthority> mappedAuthorities(AppUser appUser){
//        Collection<GrantedAuthority> mappedAuthorities = new ArrayList<>();
        appUser.getGroups().forEach( group -> {
            authorities.add( new SimpleGrantedAuthority( group.getGroupName() ) );
        });
        return authorities;
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
