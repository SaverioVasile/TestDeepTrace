package com.deeptrace.faq.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RespondentType respondentType;

    @Column(length = 255)
    private String respondentOther;

    @Column(nullable = false, length = 255)
    private String patientEmail;

    @Column(nullable = false)
    private Integer q1;
    @Column(nullable = false)
    private Integer q2;
    @Column(nullable = false)
    private Integer q3;
    @Column(nullable = false)
    private Integer q4;
    @Column(nullable = false)
    private Integer q5;
    @Column(nullable = false)
    private Integer q6;
    @Column(nullable = false)
    private Integer q7;
    @Column(nullable = false)
    private Integer q8;
    @Column(nullable = false)
    private Integer q9;
    @Column(nullable = false)
    private Integer q10;

    @Column(nullable = false)
    private Integer totalScore;

    @Column(nullable = false)
    private Boolean emailSent;

    @Column(length = 500)
    private String emailError;

    @Column(nullable = false)
    private Instant submittedAt;

    @PrePersist
    void onCreate() {
        if (submittedAt == null) {
            submittedAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public RespondentType getRespondentType() {
        return respondentType;
    }

    public void setRespondentType(RespondentType respondentType) {
        this.respondentType = respondentType;
    }

    public String getRespondentOther() {
        return respondentOther;
    }

    public void setRespondentOther(String respondentOther) {
        this.respondentOther = respondentOther;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public Integer getQ1() {
        return q1;
    }

    public void setQ1(Integer q1) {
        this.q1 = q1;
    }

    public Integer getQ2() {
        return q2;
    }

    public void setQ2(Integer q2) {
        this.q2 = q2;
    }

    public Integer getQ3() {
        return q3;
    }

    public void setQ3(Integer q3) {
        this.q3 = q3;
    }

    public Integer getQ4() {
        return q4;
    }

    public void setQ4(Integer q4) {
        this.q4 = q4;
    }

    public Integer getQ5() {
        return q5;
    }

    public void setQ5(Integer q5) {
        this.q5 = q5;
    }

    public Integer getQ6() {
        return q6;
    }

    public void setQ6(Integer q6) {
        this.q6 = q6;
    }

    public Integer getQ7() {
        return q7;
    }

    public void setQ7(Integer q7) {
        this.q7 = q7;
    }

    public Integer getQ8() {
        return q8;
    }

    public void setQ8(Integer q8) {
        this.q8 = q8;
    }

    public Integer getQ9() {
        return q9;
    }

    public void setQ9(Integer q9) {
        this.q9 = q9;
    }

    public Integer getQ10() {
        return q10;
    }

    public void setQ10(Integer q10) {
        this.q10 = q10;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Boolean getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(Boolean emailSent) {
        this.emailSent = emailSent;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public Instant getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Instant submittedAt) {
        this.submittedAt = submittedAt;
    }

    public int[] getAnswersArray() {
        return new int[]{q1, q2, q3, q4, q5, q6, q7, q8, q9, q10};
    }

    public void setAnswersArray(int[] answers) {
        this.q1 = answers[0];
        this.q2 = answers[1];
        this.q3 = answers[2];
        this.q4 = answers[3];
        this.q5 = answers[4];
        this.q6 = answers[5];
        this.q7 = answers[6];
        this.q8 = answers[7];
        this.q9 = answers[8];
        this.q10 = answers[9];
    }
}

