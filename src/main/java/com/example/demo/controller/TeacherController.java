package com.example.demo.controller;

import com.example.demo.dto.TeacherDto;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Value("${test.value}")
    private String value;

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public TeacherDto getDemoTeacher() {
        return teacherService.getDemoTeacher();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        return "Greeting from my first Spring Boot app";
    }

    @GetMapping("/bye")
    public String bye() {
        return value;
    }


}
