package com.thameem.leettrack.repository;

import com.thameem.leettrack.model.Problem;
import com.thameem.leettrack.model.Difficulty;
import com.thameem.leettrack.model.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    // That's it — JpaRepository already gives us save(), findById(), findAll(),
    // deleteById(), etc. for free, just by extending it.
    List<Problem> findByDifficulty(Difficulty difficulty);

    List<Problem> findByStatus(Status status);

    List<Problem> findByPattern(String pattern);
}