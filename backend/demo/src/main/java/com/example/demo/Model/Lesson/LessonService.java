package com.example.demo.Model.Lesson;

import java.util.*;

import com.example.demo.Model.Tutor.Tutor;
import com.example.demo.Model.Tutor.TutorRepository;
import com.example.demo.Model.TutoringAd.TutoringAd;
import com.example.demo.Model.TutoringAd.TutoringAdRepository;
import com.example.demo.Model.User.UserRepository;
import com.example.demo.Model.User.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    private final TutoringAdRepository tutoringAdRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final TutorRepository tutorRepository;


    @Autowired
    public LessonService(TutoringAdRepository tutoringAdRepository, UserRepository userRepository, LessonRepository lessonRepository, TutorRepository tutorRepository){
        this.tutoringAdRepository = tutoringAdRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.tutorRepository = tutorRepository;
    }
     public List<Lesson> getLessons(){
        return lessonRepository.findAll();
    };

    public Optional<Lesson> getLesson(Integer id) {
        return lessonRepository.findById(Objects.requireNonNullElse(id, 1));
    }

    public void addNewLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }
    public List<Optional> getTutoringAdByUserId(Integer UserId) {
        List<Optional> list = new ArrayList<Optional>();

        for(Lesson lesson : lessonRepository.findAll())
        {
            if(lesson.getUserID()==UserId)
            {
                list.addLast(tutoringAdRepository.findById(lesson.getTutoringID()));
            }
        }

        return list;
    }

    public float delLesson(int userId, int tutoringAdId) {
        Users user = userRepository.getReferenceById(userId);
        TutoringAd tutoringAd = tutoringAdRepository.getReferenceById(tutoringAdId);
        Tutor tutor = tutorRepository.getReferenceById(tutoringAd.getTutorID());
        for (Lesson lesson : lessonRepository.findAll()) {
            if (lesson.getUserID() == userId && lesson.getTutoringID() == tutoringAdId) {
                float price = tutoringAd.getPrice();
                lessonRepository.delete(lesson);
                tutoringAd = tutoringAdRepository.getReferenceById(tutoringAdId);
                user.setBalance(user.getBalance() + price);
                tutor.setBalance(tutor.getBalance() - price);
                userRepository.save(user);
                tutorRepository.save(tutor);
                tutoringAd.setAvailable(true);
                tutoringAdRepository.save(tutoringAd);
                return user.getBalance();
            }
        }
        return -1f;
    }
}
