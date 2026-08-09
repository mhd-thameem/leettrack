package com.thameem.leettrack.repository;

import com.thameem.leettrack.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    // That's it — JpaRepository already gives us save(), findById(), findAll(),
    // deleteById(), etc. for free, just by extending it.
}