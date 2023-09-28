package stee.security.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import stee.security.auth.configs.RSAKeysConfig;
import stee.security.auth.entities.AppGroup;
import stee.security.auth.entities.AppUser;
import stee.security.auth.services.AppUserService;

import java.util.ArrayList;
/**
 * Created by Steeve Jean Chilles on 09/22/2023
 */
@SpringBootApplication
@EnableConfigurationProperties( RSAKeysConfig.class )
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}


	@Bean
	CommandLineRunner start(AppUserService appUserService ){
		return args -> {
			appUserService.addGroup( new AppGroup( null, "ROLE_USER", "Desc") );
			appUserService.addGroup( new AppGroup( null, "ROLE_ADMIN", "Desc") );
			appUserService.addGroup( new AppGroup( null, "ROLE_GUEST", "Desc") );

			appUserService.save( new AppUser(null, "user", "pass", new ArrayList<>() {
			}) );
			appUserService.save( new AppUser(null, "admin", "pass", new ArrayList<>() ) );
			appUserService.save( new AppUser(null, "guest", "pass", new ArrayList<>() ) );

			appUserService.addUserToGroup("user", "ROLE_USER");
			appUserService.addUserToGroup("admin", "ROLE_USER");
			appUserService.addUserToGroup("admin", "ROLE_ADMIN");
			appUserService.addUserToGroup("guest", "ROLE_GUEST");
		};
	}

}
