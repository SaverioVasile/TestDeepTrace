package com.deeptrace.faq.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreServiceTest {

    private final ScoreService scoreService = new ScoreService();

    @Test
    void shouldCalculateTotalScore() {
        int total = scoreService.calculateTotal(List.of(0, 1, 2, 3, 4, 5, 0, 1, 2, 3));
        Assertions.assertEquals(21, total);
    }

    @Test
    void shouldRejectInvalidAnswerRange() {
        IllegalArgumentException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> scoreService.validateAnswers(List.of(0, 1, 2, 3, 4, 5, 0, 1, 2, 9))
        );
        Assertions.assertTrue(ex.getMessage().contains("domanda 10"));
    }
}

