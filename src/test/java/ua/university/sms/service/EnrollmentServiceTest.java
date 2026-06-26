package ua.university.sms.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.university.sms.model.dto.EnrollmentDTO;
import ua.university.sms.model.entity.Course;
import ua.university.sms.model.entity.Enrollment;
import ua.university.sms.model.entity.Student;
import ua.university.sms.repository.CourseRepository;
import ua.university.sms.repository.EnrollmentRepository;
import ua.university.sms.repository.StudentRepository;
import ua.university.sms.mapper.EnrollmentMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private EnrollmentMapper enrollmentMapper;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    void enrollStudent_ShouldCreateEnrollmentWithPaidFalse() {
        // Arrange
        EnrollmentDTO inputDto = new EnrollmentDTO();
        inputDto.setStudentId(1L);
        inputDto.setCourseId(2L);

        Student student = new Student();
        student.setId(1L);
        Course course = new Course();
        course.setId(2L);
        Enrollment savedEnrollment = new Enrollment();
        savedEnrollment.setPaid(false);

        EnrollmentDTO outputDto = new EnrollmentDTO();
        outputDto.setPaid(false);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(savedEnrollment);
        when(enrollmentMapper.toDTO(savedEnrollment)).thenReturn(outputDto);

        // Act
        EnrollmentDTO result = enrollmentService.enrollStudent(inputDto);

        // Assert
        assertNotNull(result);
        assertFalse(result.isPaid());
        verify(enrollmentRepository, times(1)).save(any(Enrollment.class));
    }

    @Test
    void updateGrade_ShouldUpdateGradeAndTriggerGpaRecalculation() {
        // Arrange
        Long enrollmentId = 1L;
        Double newGrade = 4.5;

        Student student = new Student();
        student.setId(10L);
        Enrollment enrollment = new Enrollment();
        enrollment.setId(enrollmentId);
        enrollment.setStudent(student);

        EnrollmentDTO outputDto = new EnrollmentDTO();
        outputDto.setGrade(newGrade);

        when(enrollmentRepository.findById(enrollmentId)).thenReturn(Optional.of(enrollment));
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);
        when(enrollmentMapper.toDTO(enrollment)).thenReturn(outputDto);

        // Act
        EnrollmentDTO result = enrollmentService.updateGrade(enrollmentId, newGrade);

        // Assert
        assertEquals(newGrade, result.getGrade());
        verify(studentService, times(1)).recalculateGPA(10L);
    }
}