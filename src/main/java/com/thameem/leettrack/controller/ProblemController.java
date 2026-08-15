package com.thameem.leettrack.controller;

import com.thameem.leettrack.model.Difficulty;
import com.thameem.leettrack.model.Status;
import com.thameem.leettrack.model.Problem;
import com.thameem.leettrack.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
public List<Problem> getAllProblems(
        @RequestParam(required = false) Difficulty difficulty,
        @RequestParam(required = false) Status status,
        @RequestParam(required = false) String pattern) {

    if (difficulty != null) {
        return problemService.getProblemsByDifficulty(difficulty);
    }
    if (status != null) {
        return problemService.getProblemsByStatus(status);
    }
    if (pattern != null) {
        return problemService.getProblemsByPattern(pattern);
    }
    return problemService.getAllProblems();
    }

    @GetMapping("/stats")
    public java.util.Map<String, Object> getStats() {
    return problemService.getStats();
    }

    @GetMapping("/{id}")
    public Problem getProblemById(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }

    @PostMapping
    public Problem createProblem(@RequestBody Problem problem) {
        return problemService.createProblem(problem);
    }

    @PutMapping("/{id}")
    public Problem updateProblem(@PathVariable Long id, @RequestBody Problem problem) {
        return problemService.updateProblem(id, problem);
    }

    @DeleteMapping("/{id}")
    public void deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
    }
}