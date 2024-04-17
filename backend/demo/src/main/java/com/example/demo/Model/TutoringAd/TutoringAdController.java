package com.example.demo.Model.TutoringAd;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Lesson.Lesson;
import com.example.demo.Model.Tutor.Tutor;
import com.example.demo.Model.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/tutoringAd")
@CrossOrigin(origins = "http://localhost:4200")
public class TutoringAdController {

    private final TutoringAdService tutoringAdService;
    private final UserService userService;

    @Autowired
    public TutoringAdController(TutoringAdService tutoringAdService, UserService userService){
        this.tutoringAdService = tutoringAdService;
        this.userService = userService;
    }

    @GetMapping
    public List<TutoringAd> getTutoringAds(){
        return tutoringAdService.getTutoringAds();
    }

    @GetMapping("/getTutoringAd")
    @ResponseBody
    public Optional<TutoringAd> getTutoringAd(@RequestParam Integer id) {
        return tutoringAdService.getTutoringAdID(id);
    }

    @PostMapping
    public void registerNewUser(@RequestBody TutoringAd tutoringAd){
        tutoringAdService.addNewTutoringAd(tutoringAd);
    }

    @GetMapping("/createLesson")
    public float createLessonForTutoringAd(@RequestParam int tutoringAdId, @RequestParam int userId) {
        return tutoringAdService.createLessonForTutoringAd(tutoringAdId, userId);
    }
    @GetMapping("/findTutoringAdByTutorId")
    @ResponseBody
    public List<TutoringAd> findTutoringAdByTutorId(@RequestParam Integer tutorId){
        return tutoringAdService.findTutoringAdByTutorId(tutorId);
    }
    @GetMapping("/delTutoringAd")
    @ResponseBody
    public void delTutoringAd(@RequestParam Integer tutoringAdId){
        tutoringAdService.delTutoringAd(tutoringAdId);
    }
}
