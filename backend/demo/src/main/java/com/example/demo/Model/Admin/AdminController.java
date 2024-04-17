package com.example.demo.Model.Admin;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Lesson.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }
    
    @GetMapping
    public List<Admin> getAdmins(){
        return adminService.getAdmins();
    };

    @GetMapping("/getAdmin")
    @ResponseBody
    public Optional<Admin> getTutor(@RequestParam(required = false) Integer id) {
        return adminService.getAdmin(id);
    }
}
