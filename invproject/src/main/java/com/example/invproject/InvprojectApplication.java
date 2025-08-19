package com.example.invproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.invproject.model.Users;
import com.example.invproject.repository.UserRepository;

@SpringBootApplication
public class InvprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvprojectApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

	 @Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository) {
        return args -> {
            // Check if admin already exists
            if (userRepository.findByUserName("admin").isEmpty()) {
                Users admin = new Users();
                admin.setFullName("Abubakar");
                admin.setUserName("admin");
                admin.setPassword("admin123"); // or use BCrypt to hash it
                admin.setEmail("admin@example.com");
                admin.setRole("admin");

                userRepository.save(admin);
                System.out.println("Default admin created: username=admin, password=admin123");
            } else {
                System.out.println("Admin already exists");
            }
        };
    }
}