package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SubjectDto {

    private Long id;

    @NotEmpty(message = "name.cannot.be.empty")
    @NotBlank(message = "name.cannot.be.blank")
    @Size(min = 2, max = 50, message = "name.cannot.have.this.size")
    private String name;
    private Integer creditPoints;
    private Boolean isOptional;
    private SubjectScoringDto subjectScoring;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(Integer creditPoints) {
        this.creditPoints = creditPoints;
    }

    public Boolean getOptional() {
        return isOptional;
    }

    public void setOptional(Boolean optional) {
        isOptional = optional;
    }

    public SubjectScoringDto getSubjectScoring() {
        return subjectScoring;
    }

    public void setSubjectScoring(SubjectScoringDto subjectScoring) {
        this.subjectScoring = subjectScoring;
    }
}
