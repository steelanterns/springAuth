package stee.security.auth.configs;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import stee.security.auth.repositories.AppUserRepository;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private AppUserRepository appUserRepository;
    private RSAKeysConfig rsaKeysConfig;
    private  AppUserDetailsService appUserDetailsService;

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

        http
//              .csrf( csrf -> csrf.ignoringRequestMatchers( toH2Console() )) //no csrf protection for H2 app
                .csrf( csrf -> csrf.disable() ) //disable csrf globaly( it's my current understand
                .sessionManagement( sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .headers( headers -> headers.frameOptions((frameOptions) -> frameOptions.disable()))//disable frameOptions protection
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(toH2Console()).permitAll()
                )
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServerConfigurer ->
                        oauth2ResourceServerConfigurer.jwt(Customizer.withDefaults()))
                .httpBasic( Customizer.withDefaults() )
                .userDetailsService( appUserDetailsService );
                ;

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder (){
        return NimbusJwtDecoder.withPublicKey( rsaKeysConfig.publicKey() ).build();
    }

    @Bean
    public JwtEncoder jwtEncoder (){
        JWK jwk = new RSAKey.Builder( rsaKeysConfig.publicKey() ).privateKey( rsaKeysConfig.privateKey() ).build();
        JWKSource<SecurityContext> jwkSource;
        jwkSource = new ImmutableJWKSet<>( new JWKSet( jwk ) );
        return new NimbusJwtEncoder( jwkSource );
    }


}
