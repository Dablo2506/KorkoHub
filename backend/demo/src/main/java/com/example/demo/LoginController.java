package com.example.demo;

import com.example.demo.Model.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }
    @GetMapping
    public Map<String, Object> combineValues(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password) {
        int[] result = loginService.getUser(email, password);
        int id = result[0];
        String role;

        if(result[1]==1) role = "Tutor";
        else if (result[1] == 0) role = "User";
        else role = null;

        Map<String, Object> customResponse = new HashMap<>();
        customResponse.put("id", id);
        customResponse.put("role", role);
        return customResponse;

    }
}
