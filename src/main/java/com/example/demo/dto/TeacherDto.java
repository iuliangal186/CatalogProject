package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class TeacherDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate emplymentDate;
    private LocalDate birthDay;

    List<SubjectDto> subjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getEmplymentDate() {
        return emplymentDate;
    }

    public void setEmplymentDate(LocalDate emplymentDate) {
        this.emplymentDate = emplymentDate;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }
}
