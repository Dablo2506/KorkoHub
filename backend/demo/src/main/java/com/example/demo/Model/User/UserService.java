package com.example.demo.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.Model.Tutor.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int getUser(String email, String password) {
        List<Users> list;
        list = getUsers();
        for (int i = 0; i < list.size(); i++)
            if (email.equals(list.get(i).getEmail()) && password.equals(list.get(i).getPassword()))
                return list.get(i).getUserID();
        return -1;
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }


    public Optional<Users> getUser(Integer id) {
        return userRepository.findById(Objects.requireNonNullElse(id, 1));
    }

    public void deleteUser(Integer userId) {

        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            System.out.printf("Cannot delete user. User with id: " + userId + " does not exist");
        } else {
            userRepository.deleteById(userId);
        }
    }

    public int addNewUser(Users users) {
        Optional<Users> usersByEmail = userRepository.findUsersByEmail(users.getEmail());
        if (usersByEmail.isPresent()) {
            System.out.printf(users.getEmail());
            return -1;
        }
        userRepository.save(users);
        return users.getUserID();
    }


    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

    public Users editUser(int id, String name, String surname, String email, String password, float balance)
    {
        Users user = userRepository.findById(id).orElse(null);
        if(user != null)
        {
            user.setUserID(id);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            user.setBalance(balance);
            return userRepository.save(user);
        }
        else
            return null;

    }

}
