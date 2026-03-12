package com.deeptrace.faq.dto;

import com.deeptrace.faq.model.RespondentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class SubmissionRequest {

    @NotNull
    private RespondentType respondentType;

    private String respondentOther;

    @NotBlank
    @Email
    private String patientEmail;

    @NotNull
    @Size(min = 10, max = 10)
    private List<Integer> answers;

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

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}

