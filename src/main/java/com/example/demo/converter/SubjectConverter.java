package com.example.demo.converter;

import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.SubjectScoringDto;
import com.example.demo.model.Subject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectConverter {
    public SubjectDto mapToDto(Subject subject){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setOptional(subject.getOptional());
        subjectDto.setCreditPoints(subject.getCreditPoints());
        subjectDto.setSubjectScoring(new SubjectScoringDto(subject.getCoursePercent(), subject.getSeminaryPercent()));

        return subjectDto;
    }

    public List<SubjectDto> mapToDtos(List<Subject> subjects){
        return subjects.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Subject maptoEntity(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setName(subjectDto.getName());
        subject.setOptional(subjectDto.getOptional());
        subject.setCreditPoints(subjectDto.getCreditPoints());
        subject.setCoursePercent(subjectDto.getSubjectScoring().getCoursePercent());
        subject.setSeminaryPercent(subjectDto.getSubjectScoring().getSeminaryPercent());

        return subject;
    }

}
