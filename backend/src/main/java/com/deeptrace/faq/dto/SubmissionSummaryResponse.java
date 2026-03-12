package com.deeptrace.faq.dto;

import com.deeptrace.faq.model.RespondentType;

import java.time.Instant;
import java.util.List;

public record SubmissionSummaryResponse(
        Long id,
        String patientEmail,
        RespondentType respondentType,
        Integer totalScore,
        List<Integer> answers,
        Boolean emailSent,
        Instant submittedAt
) {
}

