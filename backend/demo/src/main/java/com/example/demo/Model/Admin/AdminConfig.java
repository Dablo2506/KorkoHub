package com.example.demo.Model.Admin;

import com.example.demo.Model.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner commandLineRunner1(AdminRepository repository) {
        return args -> {
            Admin a = new Admin(
                    1,
                    "admin",
                    "admin",
                    "admin@admin.com",
                    "admin"
            );
            Admin b = new Admin(
                    2,
                    "admin2",
                    "admin2",
                    "admin2@admin.com",
                    "admin2"
            );

            repository.saveAll(List.of(a,b));
        };
    }
}
