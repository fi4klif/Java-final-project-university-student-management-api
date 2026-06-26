package ua.university.sms.service;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.EnrollmentDTO;
import ua.university.sms.model.entity.*;
import ua.university.sms.repository.*;
import ua.university.sms.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentDTO enrollStudent(EnrollmentDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Студент не знайдений"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Курс не знайдений"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .grade(dto.getGrade())
                .paid(dto.isPaid())
                .build();

        return mapToDTO(enrollmentRepository.save(enrollment));
    }

    private EnrollmentDTO mapToDTO(Enrollment e) {
        return EnrollmentDTO.builder()
                .id(e.getId())
                .studentId(e.getStudent().getId())
                .courseId(e.getCourse().getId())
                .grade(e.getGrade())
                .paid(e.isPaid())
                .build();
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}