package com.deeptrace.faq.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    public int calculateTotal(List<Integer> answers) {
        return answers.stream().mapToInt(Integer::intValue).sum();
    }

    public void validateAnswers(List<Integer> answers) {
        if (answers == null || answers.size() != 10) {
            throw new IllegalArgumentException("Le risposte devono essere esattamente 10.");
        }
        for (int i = 0; i < answers.size(); i++) {
            Integer value = answers.get(i);
            if (value == null || value < 0 || value > 5) {
                throw new IllegalArgumentException("La risposta alla domanda " + (i + 1) + " deve essere tra 0 e 5.");
            }
        }
    }
}

