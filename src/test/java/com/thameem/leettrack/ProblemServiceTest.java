package com.thameem.leettrack;

import com.thameem.leettrack.model.Difficulty;
import com.thameem.leettrack.model.Problem;
import com.thameem.leettrack.repository.ProblemRepository;
import com.thameem.leettrack.service.ProblemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProblemServiceTest {

    @Mock
    private ProblemRepository problemRepository;

    private ProblemService problemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        problemService = new ProblemService(problemRepository);
    }

    @Test
    void getProblemById_returnsProblem_whenFound() {
        Problem fakeProblem = new Problem("Two Sum", Difficulty.EASY, "Hash Map", "https://leetcode.com/problems/two-sum");

        when(problemRepository.findById(1L)).thenReturn(Optional.of(fakeProblem));

        Problem result = problemService.getProblemById(1L);

        assertEquals("Two Sum", result.getTitle());
        assertEquals(Difficulty.EASY, result.getDifficulty());
    }
}