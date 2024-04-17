package com.example.demo.Model.Admin;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.Model.Lesson.Lesson;
import com.example.demo.Model.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }
    public List<Admin> getAdmins(){
        /*return List.of(
            new Admin(
                1,
                "giga",
                "admin",
                "giga@admin.com",
                "kremowka"
            )
        );*/
        return adminRepository.findAll();
    };

    public Optional<Admin> getAdmin(Integer id) {
        return adminRepository.findById(Objects.requireNonNullElse(id, 1));
    }
}
