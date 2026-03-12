package com.deeptrace.faq.dto;

public record SubmissionResponse(
        Long submissionId,
        Integer totalScore,
        boolean emailSent,
        String message
) {
}

