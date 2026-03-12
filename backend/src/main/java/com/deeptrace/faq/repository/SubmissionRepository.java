package com.deeptrace.faq.repository;

import com.deeptrace.faq.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findTop50ByOrderBySubmittedAtDesc();
}

