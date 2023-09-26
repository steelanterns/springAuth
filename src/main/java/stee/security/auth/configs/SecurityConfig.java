package stee.security.auth.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import stee.security.auth.repositories.AppUserRepository;
import stee.security.auth.services.AppUserService;

import java.nio.file.AccessDeniedException;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity( prePostEnabled = true ) //this is for Authorize
@AllArgsConstructor
public class SecurityConfig {

    private AppUserRepository appUserRepository;
    private AppUserDetailsService appUserDetailsService;

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

//        return new AppUserDetailsService(appUserRepository);
        return new AppUserDetailsService(appUserRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //return http.csrf().disable()
//               return http.authorizeHttpRequests()
//                .requestMatchers("/h2-console").permitAll()
//                .and()
//
//                .authorizeHttpRequests()
//                .requestMatchers("/users")
//                .authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .build();

//        http.authorizeHttpRequests(auth -> auth.requestMatchers(toH2Console()).permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers(AntPathRequestMatcher
//                        .antMatcher("/users")).authenticated() )
//                .headers(headers -> headers.frameOptions().disable())
//                .csrf(csrf -> csrf .ignoringRequestMatchers(toH2Console()));
        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(toH2Console()).permitAll()
//                        .anyRequest().authenticated()
//                )
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                ).csrf( csrf -> csrf.ignoringRequestMatchers( toH2Console() )) //no csrf protection for H2 app
//                .csrf( csrf -> csrf.disable()) //disable csrf globaly( it's my current understand
                .headers( headers -> headers.frameOptions((frameOptions) -> frameOptions.disable()))//disable frameOptions protection
                .userDetailsService( appUserDetailsService );
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .rememberMe(Customizer.withDefaults());

        return http.build();
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
