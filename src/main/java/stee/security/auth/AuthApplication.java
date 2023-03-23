package stee.security.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import stee.security.auth.entities.SteeGroup;
import stee.security.auth.entities.SteeUser;
import stee.security.auth.services.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}


	@Bean
	CommandLineRunner start(UserService userService ){
		return args -> {
			userService.addGroup( new SteeGroup( null, "USER", "Desc") );
			userService.addGroup( new SteeGroup( null, "ADMIN", "Desc") );
			userService.addGroup( new SteeGroup( null, "GUEST", "Desc") );

			userService.add( new SteeUser(null, "user", "pass", new ArrayList<>() {
			}) );
			userService.add( new SteeUser(null, "admin", "pass", new ArrayList<>() ) );
			userService.add( new SteeUser(null, "guest", "pass", new ArrayList<>() ) );

			userService.addUserToGroup("user", "USER");
			userService.addUserToGroup("admin", "USER");
			userService.addUserToGroup("admin", "ADMIN");
			userService.addUserToGroup("guest", "GUEST");
		};
	}

}
