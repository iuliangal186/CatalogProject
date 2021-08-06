package com.example.demo.config;

import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${insert.start.data}")
public class DataSetup implements ApplicationRunner {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

//    @Value("${insert.start.data}")
//    private Boolean insertData;

    @Autowired
    public DataSetup(SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        if (insertData) {
            saveSubject("OOP", 5, false, 60, 40);
            saveSubject("math", 6, false, 70, 30);
            saveSubject("Chemistry", 4, true, 60, 40);
            saveSubject("Biology", 5, false, 40, 60);
            saveSubject("Sport", 2, true, 60, 40);
            saveSubject("Data Structures", 5, false, 10, 90);

            addTeacher("FirstName1", "LastName1", "2344565748391", 99999L);
            addTeacher("FirstName1", "LastName2", "2344565748392", 99999L);
            addTeacher("FirstName3", "LastName1", "2344565748393", 99999L);
            addTeacher("FirstName4", "LastName4", "2344565748394", 99999L);
            addTeacher("FirstName5", "LastName5", "2344565748395", 99999L);
 //       }

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

    private void addTeacher(String firstName, String lastName, String cnp, Long salary) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setCnp(cnp);
        teacher.setSalary(salary);

        teacherRepository.save(teacher);
    }
}
