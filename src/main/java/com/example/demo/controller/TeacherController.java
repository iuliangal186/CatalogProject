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
import org.springframework.web.bind.annotation.*;

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
}
