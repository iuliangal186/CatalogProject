package com.example.demo.config;

import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSetup implements ApplicationRunner {
    private final SubjectRepository subjectRepository;

    @Autowired
    public DataSetup(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveSubject("OOP", 5, false, 60, 40);
        saveSubject("math", 6, false, 70, 30);
        saveSubject("Chemistry", 4, true, 60, 40);
        saveSubject("Biology", 5, false, 40, 60);
        saveSubject("Sport", 2, true, 60, 40);
        saveSubject("Data Structures", 5, false, 10, 90);


    }

    private void saveSubject(String name, Integer creditPoints, Boolean isOptional, Integer coursePercent, Integer seminaryPercent) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCreditPoints(creditPoints);
        subject.setOptional(isOptional);
        subject.setCoursePercent(coursePercent);
        subject.setSeminaryPercent(seminaryPercent);

        subjectRepository.save(subject);
    }

}
