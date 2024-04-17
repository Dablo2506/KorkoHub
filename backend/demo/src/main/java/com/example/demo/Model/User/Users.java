package com.example.demo.Model.User;

import jakarta.persistence.*;

@Entity
@Table
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private int userID;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Float balance = 1000.0f;

    public Users(int userID, String name, String surname, String email, String password, Float balance) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public Users(String name, String surname, String email, String password, Float balance) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public Users() {

    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Float getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
