package com.example.demo.Model.Lesson;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Tutor.Tutor;
import com.example.demo.Model.TutoringAd.TutoringAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/lesson")
@CrossOrigin(origins = "http://localhost:4200")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<Lesson> getLessons(){
        return lessonService.getLessons();
    };

    @GetMapping("/getLesson")
    @ResponseBody
    public Optional<Lesson> getLesson(@RequestParam(required = false) Integer id) {
        return lessonService.getLesson(id);
    }

    @PostMapping
    void registerNewLesson(@RequestBody Lesson lesson){
        lessonService.addNewLesson(lesson);
    }

    @GetMapping("/getTutoringAdByUserId")
    @ResponseBody
    public List<Optional> getTutoringAdByUserId(@RequestParam Integer id) {
        return lessonService.getTutoringAdByUserId(id);
    }

    @GetMapping("/delLesson")
    public float delLesson(int userId, int tutoringAdId){
        return lessonService.delLesson(userId,tutoringAdId);
    };

}
