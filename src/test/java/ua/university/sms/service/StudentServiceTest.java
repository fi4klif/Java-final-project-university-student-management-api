package ua.university.sms.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.university.sms.repository.StudentRepository;
import ua.university.sms.mapper.StudentMapper;
import ua.university.sms.model.entity.Student;
import ua.university.sms.model.dto.StudentDTO;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getStudentById_ShouldReturnDTO_WhenStudentExists() {
        // Arrange
        Long id = 1L;
        Student student = new Student();
        student.setId(id);
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentMapper.toDTO(student)).thenReturn(new StudentDTO());

        // Act
        StudentDTO result = studentService.getStudentById(id);

        // Assert
        assertNotNull(result);
        verify(studentRepository).findById(id);
    }
}