package com.example.demo.converter;

import com.example.demo.dto.TeacherBasicInfoDto;
import com.example.demo.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherBasicInfoConverter {

    private final SubjectConverter subjectConverter;

    @Autowired
    public TeacherBasicInfoConverter(SubjectConverter subjectConverter) {
        this.subjectConverter = subjectConverter;
    }

    public List<TeacherBasicInfoDto> mapToDtos(List<Teacher> teachers) {
        return teachers.stream()
                .map(this::mapToDtos)
                .collect(Collectors.toList());
    }

    public TeacherBasicInfoDto mapToDtos(Teacher teachers) {
        TeacherBasicInfoDto dto = new TeacherBasicInfoDto();
        dto.setId(teachers.getId());
        dto.setFirstName(teachers.getFirstName());
        dto.setLastName(teachers.getLastName());
        dto.setSubjects(subjectConverter.mapToDtos(teachers.getSubjects()));
        return dto;
    }
}
