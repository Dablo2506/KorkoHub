package com.example.demo.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    //@Query("select u from Users u where u.email = ?1")
    Optional<Users> findUsersByEmail(String email);
}
