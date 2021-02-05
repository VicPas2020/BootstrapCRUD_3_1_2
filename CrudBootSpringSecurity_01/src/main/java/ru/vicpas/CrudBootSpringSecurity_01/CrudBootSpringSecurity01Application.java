package ru.vicpas.CrudBootSpringSecurity_01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vicpas.CrudBootSpringSecurity_01.model.Role;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.repository.RoleRepo;
import ru.vicpas.CrudBootSpringSecurity_01.repository.UserRepo;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CrudBootSpringSecurity01Application {

	public static void main(String[] args) {
		SpringApplication.run(CrudBootSpringSecurity01Application.class, args);
	}



	// Создает таблицу ролей
	@Order(1)
	@Bean
	CommandLineRunner initDatabase__AnyNamePossible_Roles(RoleRepo roleRepository) {
		return args -> {
			if (!roleRepository.existsById(1L)) {
				roleRepository.save(new Role(1L, "ROLE_ADMIN"));
				roleRepository.save(new Role(2L, "ROLE_USER"));
			}
		};
	}
	// Создает одного Админа (root root)
	@Order(2)
	@Bean
	CommandLineRunner initDatabase__Admin(UserRepo userRepository ) {
		return args -> {
			if (userRepository.count() == 0   ) {
				Set<Role> set = new HashSet<>();
				set.add(new Role(1L, "ROLE_ADMIN"));

				PasswordEncoder passwordEncoder =
						PasswordEncoderFactories.createDelegatingPasswordEncoder();
				String encodedPass = passwordEncoder.encode("root");

				userRepository.save(new User("Ru", "Bel", "8M", "root", encodedPass, set));
			}
		};
	}
}