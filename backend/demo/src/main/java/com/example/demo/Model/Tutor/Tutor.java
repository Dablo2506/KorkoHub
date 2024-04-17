package com.example.demo.Model.Tutor;
import jakarta.persistence.*;

@Entity
@Table
public class Tutor {

    @Id
    @SequenceGenerator(
            name = "Tutor_sequence",
            sequenceName = "Tutor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Tutor_sequence"
    )
    int tutorID;
    String name;
    String surname;
    String email;
    String password;
    float balance;

    public String getSubject() {
        return subject;
    }

    String subject;

    public Tutor() {

    }

    public Tutor(int tutorID, String name, String surname, String email, String password, float balance, String sub) {
        this.tutorID = tutorID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.subject = sub;
    }

    public Tutor(String name, String surname, String email, String password, float balance, String subject) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.subject = subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "subject=" + subject +
                ", tutorID=" + tutorID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
