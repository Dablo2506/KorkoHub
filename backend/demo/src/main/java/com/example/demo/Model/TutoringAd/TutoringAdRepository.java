package com.example.demo.Model.TutoringAd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutoringAdRepository extends JpaRepository<TutoringAd, Integer> {
}
