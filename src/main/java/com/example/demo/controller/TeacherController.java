package com.example.demo.controller;

import com.example.demo.converter.TeacherBasicInfoConverter;
import com.example.demo.converter.TeacherConverter;
import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.TeacherBasicInfoDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
//
//    @Value("${test.value}")
//    private String value;
//
//    private TeacherService teacherService;
//
//    @Autowired
//    public TeacherController(TeacherService teacherService){
//        this.teacherService = teacherService;
//    }
//
////    @GetMapping("/")
////    public TeacherDto getDemoTeacher() {
////        return teacherService.getDemoTeacher();
////    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/hello")
//    public String hello() {
//        return "Greeting from my first Spring Boot app";
//    }
//
//    @GetMapping("/bye")
//    public String bye() {
//        return value;
//    }
//

    private final TeacherService teacherService;
    private final TeacherConverter teacherConverter;
    private final TeacherBasicInfoConverter teacherBasicInfoConverter;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherConverter teacherConverter, TeacherBasicInfoConverter teacherBasicInfoConverter) {
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
        this.teacherBasicInfoConverter = teacherBasicInfoConverter;
    }

    @GetMapping(value = "/")
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();

        return teacherConverter.mapToDtos(teachers);
    }

    @GetMapping("/page")
    public List<TeacherBasicInfoDto> getAllBasicTeachersPages(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                              @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        List<Teacher> teachers;

        teachers = teacherService.getAllPagesTeachers(page, size);

        return teacherBasicInfoConverter.mapToDtos(teachers);

    }

    @GetMapping("/basic")
    public List<TeacherBasicInfoDto> getAllBasicTeacher() {
        List<Teacher> teachers = teacherService.getAllTeachers();

        return teacherBasicInfoConverter.mapToDtos(teachers);
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacher(id);
        return teacherConverter.mapToDto(teacher);
    }

    @GetMapping("/basic/{id}")
    public TeacherBasicInfoDto getBasicTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacher(id);
        return teacherBasicInfoConverter.mapToDtos(teacher);
    }

    @GetMapping("/filter")
    public List<TeacherDto> getFilterTeachers(@RequestParam(name = "cnp", required = false) String cnp,
                                              @RequestParam(name = "firstName", required = false) String firstName,
                                              @RequestParam(name = "lastName", required = false) String lastName) {
        List<Teacher> teachers;
        if(cnp != null) {
            teachers = Arrays.asList(teacherService.getTeacherByCnp(cnp));
        } else if(firstName != null && lastName != null){
            teachers = teacherService.getAllTeachersByFirstAndLastName(firstName, lastName);
        } else if(firstName != null) {
            teachers = teacherService.getAllTeachersByFirstName(firstName);
        } else if(lastName != null) {
            teachers = teacherService.getAllTeachersByLastName(lastName);
        } else {
            teachers = teacherService.getAllTeachers();
        }
        return teacherConverter.mapToDtos(teachers);
    }

    @PostMapping("")
    public TeacherDto saveTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherConverter.mapToEntity(teacherDto);

        List<Subject> subjectList = teacher.getSubjects();
        subjectList.forEach(s -> s.setId(null));
        teacher = teacherService.saveTeacher(teacher);

        return teacherConverter.mapToDto(teacher);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTeacher(@PathVariable(name = "id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @PutMapping(value = "/{id}")
    public TeacherDto updateTeacher(@PathVariable(name = "id") Long id,
                                    @Valid @RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherConverter.mapToEntity(teacherDto);
        teacher = teacherService.updateTeacher(id, teacher);

        return teacherConverter.mapToDto(teacher);
    }

    @PutMapping("/{teacherId}/subject/{subjectId}")
    public TeacherDto assignSubjectToTeacher(@PathVariable(name = "teacherId") Long teacherId,
                                             @PathVariable(name = "subjectId") Long subjectId) {
        Teacher teacher = teacherService.assignSubjectToTeacher(teacherId, subjectId);
        return teacherConverter.mapToDto(teacher);
    }
}
