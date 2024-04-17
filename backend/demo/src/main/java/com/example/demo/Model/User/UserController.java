package com.example.demo.Model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<Users> getUser(){
        return userService.getUsers();
    };

    @GetMapping("/login")
    public int combineValues(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password) {
        return userService.getUser(email, password);
    }


    @GetMapping("/getUser")
    @ResponseBody
    public Optional<Users> getUser(@RequestParam(required = false) Integer id) {
        return userService.getUser(id);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
    }

    @PostMapping
    public int registerNewUser(@RequestBody Users users){
        return userService.addNewUser(users);
    }


    @PatchMapping(path = "{userId}")
    public ResponseEntity<Users> patchUser(@PathVariable Integer userId, @RequestBody Users user) {
        Users existingUser = userService.getUser(userId).orElse(null);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }

        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        if (user.getSurname() != null) {
            existingUser.setSurname(user.getSurname());
        }

        if (user.getBalance() != null) {
            existingUser.setBalance(user.getBalance());
        }

        return ResponseEntity.ok(userService.updateUser(existingUser));
    }

    @GetMapping("/editUser")
    @ResponseBody
    public Users editUser(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "name") String imie,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "balance") float balance)
    {
        return userService.editUser(id,imie,surname,email,password,balance);
    }
}