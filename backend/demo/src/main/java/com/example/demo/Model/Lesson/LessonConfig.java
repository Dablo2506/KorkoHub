package com.example.demo.Model.Lesson;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class LessonConfig {
    @Bean
    CommandLineRunner commandLineRunner2(LessonRepository repository){
        return args -> {
            Lesson matematyka = new Lesson(
                    1,
                    1,
                    1,
                    (float)50.00
            );
            Lesson fizyka = new Lesson(
                    2,
                    2,
                    1,
                    (float)50.00
            );


            repository.saveAll(List.of(matematyka, fizyka));
        };
    }
}
