package com.example.demo.Model.TutoringAd;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class TutoringAd {

    @Id
    @SequenceGenerator(
            name = "TutoringAd_sequence",
            sequenceName = "TutoringAd_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TutoringAd_sequence"
    )
    int tutoringAdID;
    String subject;
    int duration = 60;
    int tutorID;
    Date date;
    boolean available = true;
    String description;
    float price;

    

    public TutoringAd(int tutoringAdID, String subject, int duration, int tutorID, Date date, boolean available, String description, float price) {
        this.tutoringAdID = tutoringAdID;
        this.subject = subject;
        this.duration = duration;
        this.tutorID = tutorID;
        this.date = date;
        this.available = available;
        this.description = description;
        this.price = price;
    }

    public TutoringAd(int tutoringAdID, String subject, int duration, int tutorID, Date date, boolean available) {
        this.tutoringAdID = tutoringAdID;
        this.subject = subject;
        this.duration = duration;
        this.tutorID = tutorID;
        this.date = date;
        this.available = available;
    }

    public TutoringAd(String subject, int duration, int tutorID, Date date, boolean available) {
        this.subject = subject;
        this.duration = duration;
        this.tutorID = tutorID;
        this.date = date;
        this.available = available;
    }

    public TutoringAd() {

    }


    public int getTutoringAdID() {
        return tutoringAdID;
    }

    public void setTutoringAdID(int tutoringAdID) {
        this.tutoringAdID = tutoringAdID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean available() {
        return available;
    }

    public void setIndividual(boolean individual) {
        available = individual;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice(){
        return this.price;
    }

    public void setPrice(float price){
        this.price = price;
    }

    @Override
    public String toString() {
        return "TutoringAd{" +
                "tutoringAdID=" + tutoringAdID +
                ", subject='" + subject + '\'' +
                ", duration=" + duration +
                ", tutorID=" + tutorID +
                ", date=" + date +
                ", available=" + available +
                ", description=" + description +
                ", price=" + price +
                '}';
    }
}
