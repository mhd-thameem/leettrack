package com.thameem.leettrack.service;

import com.thameem.leettrack.model.Problem;
import com.thameem.leettrack.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    // Constructor injection: Spring automatically hands us a working
    // ProblemRepository here — we never call "new ProblemRepository()" ourselves.
    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    public Problem getProblemById(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found with id: " + id));
    }

    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public Problem updateProblem(Long id, Problem updatedProblem) {
        Problem existing = getProblemById(id);
        existing.setTitle(updatedProblem.getTitle());
        existing.setDifficulty(updatedProblem.getDifficulty());
        existing.setPattern(updatedProblem.getPattern());
        existing.setUrl(updatedProblem.getUrl());
        existing.setStatus(updatedProblem.getStatus());
        return problemRepository.save(existing);
    }

    public void deleteProblem(Long id) {
        problemRepository.deleteById(id);
    }
}