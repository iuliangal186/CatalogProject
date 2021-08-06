package com.example.demo.converter;

import com.example.demo.dto.TeacherDto;
import com.example.demo.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherConverter {

    private final SubjectConverter subjectConverter;

    @Autowired
    public TeacherConverter(SubjectConverter subjectConverter) {
        this.subjectConverter = subjectConverter;
    }

    public List<TeacherDto> mapToDtos(List<Teacher> teachers){
        return teachers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TeacherDto mapToDto(Teacher teacher){
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setCnp(teacher.getCnp());
        teacherDto.setSalary(teacher.getSalary());
        teacherDto.setEmploymentDate(teacher.getEmploymentDate());
        teacherDto.setBirthDay(teacher.getBirthDate());
        if(teacher.getSubjects() != null) {
            teacherDto.setSubjects(subjectConverter.mapToDtos(teacher.getSubjects()));
        }
        return teacherDto;
    }

    public Teacher mapToEntity(TeacherDto dto) {
        Teacher teacher = new Teacher();
        teacher.setEmploymentDate(dto.getEmploymentDate());
        teacher.setBirthDate(dto.getBirthDay());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setCnp(dto.getCnp());
        teacher.setSalary(dto.getSalary());
        if(dto.getSubjects() != null) {
            teacher.setSubjects(subjectConverter.mapToEntities(dto.getSubjects()));
        }
        return teacher;
    }
}
