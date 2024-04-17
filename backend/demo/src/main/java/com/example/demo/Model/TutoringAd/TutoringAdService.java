package com.example.demo.Model.TutoringAd;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Lesson.Lesson;
import com.example.demo.Model.Lesson.LessonRepository;
import com.example.demo.Model.Tutor.Tutor;
import com.example.demo.Model.Tutor.TutorRepository;
import com.example.demo.Model.User.UserRepository;
import com.example.demo.Model.User.Users;

import jakarta.transaction.Transactional;

@Service
public class TutoringAdService {

    private final TutoringAdRepository tutoringAdRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final TutorRepository tutorRepository;

    @Autowired
    public TutoringAdService(TutoringAdRepository tutoringAdRepository, UserRepository userRepository, LessonRepository lessonRepository, TutorRepository tutorRepository){
        this.tutoringAdRepository = tutoringAdRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.tutorRepository = tutorRepository;
    }

    public List<TutoringAd> getTutoringAds(){
        List<TutoringAd> list = new ArrayList<TutoringAd>();;
        for(TutoringAd tutoringAd : tutoringAdRepository.findAll())
        {
            if(tutoringAd.isAvailable())
            {
                list.add(tutoringAd);
            }
        }
        return list;

    }

    public Optional<TutoringAd> getTutoringAdID(Integer id) {

        return tutoringAdRepository.findById(Objects.requireNonNullElse(id, 1));
    }

    public void addNewTutoringAd(TutoringAd tutoringAd) {
        tutoringAdRepository.save(tutoringAd);
    }


    @Transactional
    public Float createLessonForTutoringAd(int tutoringAdId, int userId) {
        Optional<TutoringAd> tutoringAdOptional = tutoringAdRepository.findById(tutoringAdId);
        Optional<Users> userOptional = userRepository.findById(userId); 
        
        if (tutoringAdOptional.isPresent() && userOptional.isPresent()) {
            TutoringAd tutoringAd = tutoringAdOptional.get();
            
            float price = tutoringAd.getPrice();

            Lesson lesson = new Lesson();
            lesson.setTutoringID(tutoringAd.getTutoringAdID());
            lesson.setUserID(userId);
            lesson.setPrice(price);

        
            Optional<Tutor> tutorOptional = tutorRepository.findById(tutoringAd.getTutorID());
            if (tutorOptional.isPresent()) {
                Users user = userOptional.get();
                Tutor tutor = tutorOptional.get();
                
                if(user.getBalance() >= price ){
                    tutoringAd.setIndividual(false);
                    user.setBalance(user.getBalance() - price);
                    tutor.setBalance(tutor.getBalance() + price);
                    userRepository.save(user);
                    tutorRepository.save(tutor);
                    lessonRepository.save(lesson);
                    return user.getBalance();
                } else {
                    System.out.println("Nie masz wystarczających środków na koncie");
                    return -1f;
                }
                
            } else {
                System.out.println("Nie znaleziono korepetytora z ID: " + tutoringAd.getTutorID());
                return -2f;
            }
            
        } else {
            System.out.println("Wystapił błąd");
            return -3f;
        }
    }
    public List<TutoringAd> findTutoringAdByTutorId(Integer tutorId)
    {
        List<TutoringAd> list = new ArrayList<TutoringAd>();
        for(TutoringAd tutoringAd : tutoringAdRepository.findAll())
        {
            if(tutoringAd.getTutorID() == tutorId)
            {
                list.add(tutoringAd);


            }
        }
        return list;
    }
    public void delTutoringAd(Integer tutoringAdId)
    {
        TutoringAd tutoringAd = new TutoringAd();
        Tutor tutor = new Tutor();
        Users user = new Users();
        tutoringAd = tutoringAdRepository.getReferenceById(tutoringAdId);
        tutoringAdRepository.delete(tutoringAd);
        for(Lesson lesson : lessonRepository.findAll())
        {
            if(lesson.getTutoringID()==tutoringAdId)
            {

                lessonRepository.delete(lesson);
            }
        }

    }

}