package com.example.demo.Model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            Users Jan = new Users(
                    1,
                    "Jan",
                    "Kowalski",
                    "jan@kowalski.com",
                    "qwerty12",
                    (float)1300.00
            );
            Users Pawel = new Users(
                    2,
                    "Paweł",
                    "Pawłowski",
                    "pawel123@gmail.com",
                    "haslo123",
                    (float)10.00
            );

            repository.saveAll(List.of(Jan, Pawel));
        };
    }
}
