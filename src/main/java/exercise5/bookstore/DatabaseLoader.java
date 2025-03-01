package exercise5.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import exercise5.bookstore.model.AppUser;
import exercise5.bookstore.repository.UserRepository;

@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Create a user with username "user", password "password", email "user@example.com", and role "USER"
            AppUser user = new AppUser("user", passwordEncoder.encode("user"), "user@example.com", "USER");
            userRepository.save(user);

            // Create an admin with username "admin", password "admin", email "admin@example.com", and role "ADMIN"
            AppUser admin = new AppUser("admin", passwordEncoder.encode("admin"), "admin@example.com", "ADMIN");
            userRepository.save(admin);
        };
    }
}