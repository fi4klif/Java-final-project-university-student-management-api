package ua.university.sms.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.university.sms.exception.ResourceNotFoundException;
import ua.university.sms.repository.EnrollmentRepository;
import ua.university.sms.repository.StudentRepository;
import ua.university.sms.mapper.StudentMapper;
import ua.university.sms.model.entity.Enrollment;
import ua.university.sms.model.entity.Student;
import ua.university.sms.model.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getStudentById_ShouldReturnDTO_WhenStudentExists() {
        Long id = 1L;
        Student student = new Student();
        student.setId(id);
        StudentDTO dto = new StudentDTO();
        dto.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentMapper.toDTO(student)).thenReturn(dto);

        StudentDTO result = studentService.getStudentById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(studentRepository).findById(id);
    }

    @Test
    void getStudentById_ShouldThrowException_WhenStudentNotFound() {
        Long id = 99L;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(id));
        verify(studentRepository).findById(id);
    }

    @Test
    void createStudent_ShouldReturnSavedDTO() {
        StudentDTO inputDto = new StudentDTO();
        inputDto.setFirstName("Ivan");
        Student student = new Student();
        student.setFirstName("Ivan");

        when(studentMapper.toEntity(inputDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(inputDto);

        StudentDTO result = studentService.createStudent(inputDto);

        assertEquals("Ivan", result.getFirstName());
        verify(studentRepository).save(student);
    }

    @Test
    void deleteStudent_ShouldCallRepositoryDelete() {
        Long id = 1L;
        doNothing().when(studentRepository).deleteById(id);

        studentService.deleteStudent(id);

        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    void recalculateGPA_ShouldUpdateGpa_WhenEnrollmentsExist() {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);

        Enrollment e1 = new Enrollment();
        e1.setGrade(4.0);
        Enrollment e2 = new Enrollment();
        e2.setGrade(5.0);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(enrollmentRepository.findByStudentId(studentId)).thenReturn(List.of(e1, e2));

        studentService.recalculateGPA(studentId);

        assertEquals(4.5, student.getGpa());
        verify(studentRepository).save(student);
    }
}