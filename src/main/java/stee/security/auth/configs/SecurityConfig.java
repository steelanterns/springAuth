package stee.security.auth.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import stee.security.auth.repositories.SteeUserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //this is for Authorize
public class SecurityConfig {

    private SteeUserRepository steeUserRepository;

    public SecurityConfig(SteeUserRepository steeUserRepository) {
        this.steeUserRepository = steeUserRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("stee")
//                .password(passwordEncoder.encode("stee"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("freud")
//                .password(passwordEncoder.encode("stee"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);

        return new SteeUserDetailsService(steeUserRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/h2-console").permitAll()
                .and()

                .authorizeHttpRequests()
                .requestMatchers("/users")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService( userDetailsService() );
        daoAuthenticationProvider.setPasswordEncoder( passwordEncoder());

        return daoAuthenticationProvider;
    }

}
