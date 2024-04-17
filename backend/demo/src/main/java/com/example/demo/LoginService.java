package com.example.demo;

import com.example.demo.Model.Tutor.Tutor;
import com.example.demo.Model.Tutor.TutorRepository;
import com.example.demo.Model.User.UserRepository;
import com.example.demo.Model.User.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final TutorRepository tutorRepository;

    @Autowired
    public LoginService(UserRepository userRepository, TutorRepository tutorRepository){
        this.userRepository = userRepository;
        this.tutorRepository = tutorRepository;
    }

    public int[] getUser(String email, String password){
        int[] result = {-1, -1};
        List<Users> list;
        list = getUsers();
        for(int i=0; i<list.size(); i++)
            if(email.equals(list.get(i).getEmail()) && password.equals(list.get(i).getPassword())){
                result[0] = list.get(i).getUserID();
                result[1] = 0;
                return result;
            }
        List<Tutor> tutorList;
        tutorList = getTutors();
        for(int i=0; i<tutorList.size(); i++)
            if(email.equals(tutorList.get(i).getEmail()) && password.equals(tutorList.get(i).getPassword())){
                result[0] = tutorList.get(i).getTutorID();
                result[1] = 1;
                return result;
            }
        return result;
    }

    public List<Users> getUsers() {
        return  userRepository.findAll();
    }

    public List<Tutor> getTutors() {
        return  tutorRepository.findAll();
    }
}
