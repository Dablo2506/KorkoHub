package com.example.demo.Model.Lesson;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Lesson {

    @Id
    @SequenceGenerator(
            name = "Lesson_sequence",
            sequenceName = "Lesson_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Lesson_sequence"
    )
    int lessonID;
    int tutoringAdID;
    int userID;
    float price;
    

    public Lesson(int lessonID, int tutoringAdID, int userID, float price) {
        this.lessonID = lessonID;
        this.tutoringAdID = tutoringAdID;
        this.userID = userID;
        this.price = price;
    }

    public Lesson(Date date, int tutoringAdID, int userID) {
        this.tutoringAdID = tutoringAdID;
        this.userID = userID;
    }

    public Lesson() {

    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }


    public int getTutoringID() {
        return tutoringAdID;
    }

    public void setTutoringID(int tutoringAdID) {
        this.tutoringAdID = tutoringAdID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public float getPrice(){
        return this.price;
    }

    public void setPrice(float price){
        this.price = price;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonID=" + lessonID +
                ", tutoringAdID=" + tutoringAdID +
                ", userID=" + userID +
                '}';
    }
}
