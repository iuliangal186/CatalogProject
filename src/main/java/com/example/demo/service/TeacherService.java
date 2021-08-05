package com.example.demo.service;

import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.SubjectScoringDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        return optionalTeacher.orElseThrow(() -> new NotFoundException("Teacher not found", "teacher.not.found"));
    }

    //    public TeacherDto getDemoTeacher() {
//        TeacherDto teacher = new TeacherDto();
//        teacher.setId(1L);
//        teacher.setFirstName("Ionel");
//        teacher.setLastName("Popescu");
//        teacher.setBirthDay(LocalDate.now().minusYears(35));
//        teacher.setEmploymentDate(LocalDate.now().minusYears(3));
//        teacher.setSubjects(getDemoSubjects());
//
//        return teacher;
//    }
//
//    private List<SubjectDto> getDemoSubjects() {
//        SubjectDto subject = new SubjectDto();
//        subject.setId(1L);
//        subject.setName("OOP");
//        subject.setSubjectScoring(new SubjectScoringDto(50, 50));
//        subject.setCreditPoints(5);
//        subject.setOptional(false);
//
//        return Arrays.asList(subject, subject);
//    }
}
