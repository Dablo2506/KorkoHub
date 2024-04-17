package com.example.demo.Model.Tutor;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.User.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/tutor")
@CrossOrigin(origins = "http://localhost:4200")
public class TutorController {

    
    private final TutorService tutorService;

    @Autowired
    public TutorController(TutorService tutorService){
        this.tutorService = tutorService;
    }
    

    @GetMapping
    public List<Tutor> getTutors(){
        return tutorService.getTutors();
    };

    @DeleteMapping(path = "{tutorId}")
    public void deleteUser(@PathVariable("tutorId") Integer tutorId){
        tutorService.deleteTutor(tutorId);
    }

    @PostMapping
    public int registerNewTutor(@RequestBody Tutor tutor){
        return tutorService.addNewTutor(tutor);
    }

    @GetMapping("/getTutor")
    @ResponseBody
    public Optional<Tutor> getTutor(@RequestParam(required = false) Integer id) {
        return tutorService.getTutor(id);
    }

    @GetMapping("/editTutor")
    @ResponseBody
    public Tutor editTutor(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "name") String imie,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "balance") float balance,
            @RequestParam(name = "subject") String subject)
    {
        return tutorService.editTutors(id,imie,surname,email,password,balance,subject);
    }

}
