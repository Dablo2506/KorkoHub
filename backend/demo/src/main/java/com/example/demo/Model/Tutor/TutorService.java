package com.example.demo.Model.Tutor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.Model.TutoringAd.TutoringAdRepository;
import com.example.demo.Model.User.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    @Autowired
    public TutorService(TutorRepository tutorRepository){
        this.tutorRepository = tutorRepository;
    }
    public List<Tutor> getTutors(){
        return tutorRepository.findAll();
    }

    public Optional<Tutor> getTutor(Integer id) {
        return tutorRepository.findById(Objects.requireNonNullElse(id, 1));
    }

    public void deleteTutor(Integer tutorId) {
        
        boolean exists = tutorRepository.existsById(tutorId);

        if (!exists) {
            System.out.printf("Cannot delete tutor. Tutor with id: " + tutorId + " does not exist");
        } else{
            tutorRepository.deleteById(tutorId);
        }
    }

    public int addNewTutor(Tutor tutor) {
        Optional<Tutor> tutorByEmail = tutorRepository.findTutorByEmail(tutor.getEmail());
        if(tutorByEmail.isPresent())
        {
            System.out.printf(tutor.getEmail());
            return -1;
        }
        tutorRepository.save(tutor);
        return tutor.getTutorID();
    }

    public Tutor editTutors(int id, String name, String surname, String email, String password, float balance, String subject)
    {
        Tutor tutor = tutorRepository.findById(id).orElse(null);
        if(tutor != null)
        {
            tutor.setTutorID(id);
            tutor.setName(name);
            tutor.setSurname(surname);
            tutor.setEmail(email);
            tutor.setPassword(password);
            tutor.setBalance(balance);
            tutor.setSubject(subject);
            return tutorRepository.save(tutor);
        }
        else
            return null;

    }
}
