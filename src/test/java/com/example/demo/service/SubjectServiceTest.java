package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @Test
    @DisplayName("Get subject from db")
    void test_getSubject() {
        //Arrange
        Long id = 1L;
        Subject subject = new Subject();
        subject.setId(id);
        when(subjectRepository.findById(id)).thenReturn(Optional.of(subject));
        //Act
        Subject actualSubject = subjectService.getSubject(id);

        //Assert
        assertEquals(id, actualSubject.getId());
        verify(subjectRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Get subject from db")
    void test_getNoSubject() {
        //Arrange
        Long id = 1L;
        when(subjectRepository.findById(id)).thenReturn(Optional.empty());
        //Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> subjectService.getSubject(id));
        assertEquals("Subject not found", exception.getMessage());
        assertEquals("subject.not.found", exception.getErrorCode());

    }

    @Test
    @DisplayName("Save valid subject")
    void test_saveSubject() {
        //Arrange
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("OOP");
        subject.setCreditPoints(5);
        subject.setOptional(false);
        subject.setSeminaryPercent(50);
        subject.setCoursePercent(50);
        when(subjectRepository.save(subject)).thenReturn(subject);
        //Act
        Subject saved = subjectService.saveSubject(subject);

        // Assert
        verify(subjectRepository).save(subject);
        assertEquals(subject, saved);

    }

    @Test
    @DisplayName("Save invalid subject")
    void test_saveInvalidSubject() {
        //Arrange
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("OOP");
        subject.setCreditPoints(5);
        subject.setOptional(false);
        subject.setSeminaryPercent(40);
        subject.setCoursePercent(50);

        //Act
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> subjectService.saveSubject(subject));

        // Assert
        verify(subjectRepository, never()).save(subject);
        assertEquals("Subject scoring percent must be 100%", exception.getMessage());

    }
}