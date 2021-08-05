package com.example.demo.service;

import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.SubjectScoringDto;
import com.example.demo.dto.TeacherDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class TeacherService {

    public TeacherDto getDemoTeacher() {
        TeacherDto teacher = new TeacherDto();
        teacher.setId(1L);
        teacher.setFirstName("Ionel");
        teacher.setLastName("Popescu");
        teacher.setBirthDay(LocalDate.now().minusYears(35));
        teacher.setEmplymentDate(LocalDate.now().minusYears(3));
        teacher.setSubjects(getDemoSubjects());

        return teacher;
    }

    private List<SubjectDto> getDemoSubjects() {
        SubjectDto subject = new SubjectDto();
        subject.setId(1L);
        subject.setName("OOP");
        subject.setSubjectScoring(new SubjectScoringDto(50, 50));
        subject.setCreditPoints(5);
        subject.setOptional(false);

        return Arrays.asList(subject, subject);
    }
}
