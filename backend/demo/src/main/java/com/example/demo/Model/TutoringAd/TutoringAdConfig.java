package com.example.demo.Model.TutoringAd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class TutoringAdConfig {
    @Bean
    CommandLineRunner commandLineRunner4(TutoringAdRepository repository){
        return args -> {
            TutoringAd matematyka = new TutoringAd(
                    1,
                    "Matematyka",
                    60,
                    1,
                    new Date(),
                    false,
                    "Na moich zajęciach nauczysz się wielu fascynujących zagadnień ze wszystkich dziedzin matematyki. Uczę już prawie dekadę matematyki prywatnie, a także jestem wykładowcą na uczelni od 15 lat.",
                    (float)50.00
            );
            TutoringAd matematyka2 = new TutoringAd(
                    2,
                    "Matematyka",
                    60,
                    2,
                    new Date(),
                    false,
                    "Jestem absolwentką Politechniki Wrocławskiej. Z wykształcenia jestem inżynierem technologii chemicznej. Chętnie pomogę Ci przebrnąć przez problemy jakie zastałeś/aś podczas nauki tego przedmiotu",
                    (float)50.00
            );
            TutoringAd fizyka = new TutoringAd(
                    3,
                    "Fizyka",
                    60,
                    3,
                    new Date(),
                    true,
                    "Zapraszam na moje zajęcia, gdzie zgłębimy fascynujące zagadnienia z zakresu fizyki. Udało mi się nauczyć wielu studentów oraz pomóc w pisaniu matury; wielu moich uczniów osiągało wyniki ponad 80% z fizyki.",
                    (float)80.00
            );
            TutoringAd fizyka2 = new TutoringAd(
                    4,
                    "Fizyka",
                    60,
                    4,
                    new Date(),
                    true,
                    "Jestem absolwentką Technikum Informatycznego z rozszerzoną fizyką i obecnie studentką. Jako dobra koleżanka pomagałam przygotowywać moich znajomych do egzaminów i sprawdzianów z powodzeniem :)",
                    (float)50.00
                );
            TutoringAd angielski = new TutoringAd(
                    5,
                    "Język angielski",
                    60,
                    5,
                    new Date(),
                    true,
                    "Jestem nauczycielem języka angielskiego, japońskiego, filozofii i etyki od ponad 25 lat. Studiowałem między innymi w Tokyo i Dublinie. Mam ukończone kilka fakultetów, w tym studia doktoranckie.",
                    (float)100.00
            );
            TutoringAd angielski2 = new TutoringAd(
                    6,
                    "Język angielski",
                    60,
                    6,
                    new Date(),
                    true,
                    "Jestem studentką 4. roku filologii angielskiej o specjalizacji nauczycielskiej. Posiadam tytuł licencjata filologii angielskiej o specjalizacji nauczycielskiej, a obecnie jestem na studiach magisterskich.",
                    (float)80.00
            );
            TutoringAd polski = new TutoringAd(
                    7,
                    "Język polski",
                    60,
                    7,
                    new Date(),
                    true,
                    "Jestem studentką Pedagogiki Wczesnoszkolnej i Przedszkolnej. Z przyjemnością pomagam dzieciom rozwijać swoje umiejętności. Interesuje się szczególnie neurolingwistyką i logopedią.",
                    (float)50.00
            );
            TutoringAd polski2 = new TutoringAd(
                    8,
                    "Język polski",
                    60,
                    8,
                    new Date(),
                    true,
                    "Jestem przyszłą maturzystką, aktualnie uczę się na profilu humanistycznym. Uwielbiam pracę z dziećmi, mam również doświadczenie jak niania. Uwielbiam uczyć się języków.",
                    (float)50.00
            );

            repository.saveAll(List.of(matematyka, matematyka2, fizyka, fizyka2, angielski, angielski2, polski, polski2));
        };
    }
}
