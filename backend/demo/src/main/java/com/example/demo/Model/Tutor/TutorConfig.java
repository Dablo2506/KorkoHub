package com.example.demo.Model.Tutor;

import com.example.demo.Model.User.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TutorConfig {

    @Bean
    CommandLineRunner commandLineRunner3(TutorRepository repository){
        return args -> {
            Tutor Adrian = new Tutor(
                    1,
                    "Adrian",
                    "Kowalski",
                    "adrian@kowalski.com",
                    "matemaks",
                    (float)300.00,
                    "Matematyka"
            );
            Tutor Zuzanna = new Tutor(
                    2,
                    "Zuzanna",
                    "Kaczmarek",
                    "kaczmarek4343@gmail.com",
                    "dyfuzja",
                    (float)120.00,
                    "Matematyka"
            );

            Tutor Tomek = new Tutor(
                    3,
                    "Tomek",
                    "Tomaszek",
                    "tomtom01@gmail.com",
                    "paralaksa",
                    (float)1010.00,
                    "Fizyka"
            );

            Tutor Kasia = new Tutor(
                    4,
                    "Kasia",
                    "Adamczyk",
                    "Adamczyk4444@nowak.com",
                    "pitagoras",
                    (float)1220.00,
                    "Fizyka"
            );
            Tutor Tomasz = new Tutor(
                    5,
                    "Tomasz",
                    "Adamek",
                    "tomek123@gmail.com",
                    "orwell",
                    (float)160.00,
                    "Angielski"
            );
            Tutor Ania = new Tutor(
                    6,
                    "Ania",
                    "Wojcik",
                    "aniawoj@gmail.com",
                    "doyle",
                    (float)10.00,
                    "Angielski"
            );
            Tutor Ewa = new Tutor(
                    7,
                    "Ewa",
                    "Zając",
                    "Zajacewa@gmail.com",
                    "okarczuk",
                    (float)520.00,
                    "Polski"
            );
            Tutor Aleksandra = new Tutor(
                    8,
                    "Aleksandra",
                    "Jankowska",
                    "Jankowska13@gmail.com",
                    "dabrowski",
                    (float)40.00,
                    "Polski"
            );

            repository.saveAll(List.of(Adrian, Zuzanna, Tomek, Kasia, Tomasz, Ania, Ewa, Aleksandra));
        };
    }
}
