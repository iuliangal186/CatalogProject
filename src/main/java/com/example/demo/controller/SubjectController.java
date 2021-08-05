package com.example.demo.controller;

import com.example.demo.converter.SubjectConverter;
import com.example.demo.dto.SubjectDto;
import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectConverter subjectConverter;

    private Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    public SubjectController(SubjectService subjectService, SubjectConverter subjectConverter) {
        this.subjectService = subjectService;
        this.subjectConverter = subjectConverter;
    }

    @GetMapping(value = "")
    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();

        return subjectConverter.mapToDtos(subjects);
    }

    @GetMapping(value = "/{id}")
    public SubjectDto getSubject(@PathVariable("id") Long id) {
        Subject subject =  subjectService.getSubject(id);
        return subjectConverter.mapToDto(subject);
    }
//
//    @GetMapping(value = "demo")
//    public Subject getParamSubject(@RequestParam("id") Long id) {
//        return subjectService.getSubject(id);
//    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
//        try{
//            Subject subject = subjectService.getSubject(id);
//            return ResponseEntity.ok(subject);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @PostMapping("")
    public SubjectDto saveSubject(@Valid @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectConverter.maptoEntity(subjectDto);
        subject = subjectService.saveSubject(subject);

        logger.info("Saved new subject {}", subject);
        return subjectConverter.mapToDto(subject);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        logger.info("deleted subject {}", subjectService.getSubject(id));
        subjectService.deleteSubject(id);
    }

//    @PutMapping("/{id}")
//    public SubjectDto updateSubject(@PathVariable Long id, @RequestBody SubjectDto request) {
//        Subject subject = subjectConverter.maptoEntity(request);
//        subject = subjectService.updateSubject(id, subject);
//
//        return subjectConverter.mapToDto(subject);
//    }

    @PutMapping("/{id}")
    public SubjectDto updatePartialSubject(@PathVariable Long id, @RequestBody SubjectDto request) {
        Subject subject = subjectConverter.maptoEntity(request);
        subject = subjectService.updatePartialSubject(id, subject);
        logger.info("Updated subject {}", subject);

        return subjectConverter.mapToDto(subject);
    }

    @GetMapping("/points/{credit}")
    public List<SubjectDto> getAllSubjectsByCreditPoint(@PathVariable Integer credit) {
        List<Subject> subjects = subjectService.findAllByCreditPoints(credit);
        return subjectConverter.mapToDtos(subjects);
    }

    @GetMapping("/between/{creditStart}/{creditEnd}")
    public Long getNumberOfSubjectsWithCreditPointsBetween(@PathVariable Integer creditStart, @PathVariable Integer creditEnd) {
        return subjectService.getNumberOfSubjectsWithCreditPointsBetween(creditStart, creditEnd);
    }
}
