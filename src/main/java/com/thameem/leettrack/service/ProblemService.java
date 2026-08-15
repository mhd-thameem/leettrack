package com.thameem.leettrack.service;

import com.thameem.leettrack.model.Problem;
import com.thameem.leettrack.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thameem.leettrack.exception.ProblemNotFoundException;
import com.thameem.leettrack.model.Status;
import com.thameem.leettrack.model.Difficulty;

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
            .orElseThrow(() -> new ProblemNotFoundException(id));
    }

    public java.util.Map<String, Object> getStats() {
    List<Problem> allProblems = problemRepository.findAll();

    long totalProblems = allProblems.size();

    long solvedCount = allProblems.stream()
            .filter(p -> p.getStatus() == Status.SOLVED)
            .count();

    java.util.Map<Difficulty, Long> byDifficulty = allProblems.stream()
            .collect(java.util.stream.Collectors.groupingBy(
                    Problem::getDifficulty,
                    java.util.stream.Collectors.counting()
            ));

    java.util.Map<String, Long> byPattern = allProblems.stream()
            .collect(java.util.stream.Collectors.groupingBy(
                    Problem::getPattern,
                    java.util.stream.Collectors.counting()
            ));

    java.util.Map<String, Object> stats = new java.util.HashMap<>();
    stats.put("totalProblems", totalProblems);
    stats.put("solvedCount", solvedCount);
    stats.put("byDifficulty", byDifficulty);
    stats.put("byPattern", byPattern);

    return stats;
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
    public List<Problem> getProblemsByDifficulty(Difficulty difficulty) {
        return problemRepository.findByDifficulty(difficulty);
    }

    public List<Problem> getProblemsByStatus(Status status) {
        return problemRepository.findByStatus(status);
    }

    public List<Problem> getProblemsByPattern(String pattern) {
        return problemRepository.findByPattern(pattern);
    }
    public void deleteProblem(Long id) {
        problemRepository.deleteById(id);
    }
}