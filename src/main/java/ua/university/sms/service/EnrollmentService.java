package ua.university.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.EnrollmentDTO;
import ua.university.sms.model.entity.Course;
import ua.university.sms.model.entity.Enrollment;
import ua.university.sms.model.entity.Student;
import ua.university.sms.repository.CourseRepository;
import ua.university.sms.repository.EnrollmentRepository;
import ua.university.sms.repository.StudentRepository;
import ua.university.sms.exception.ResourceNotFoundException;
import ua.university.sms.mapper.EnrollmentMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentDTO enrollStudent(EnrollmentDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Студент не знайдений"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Курс не знайдений"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrollmentDate(dto.getEnrollmentDate() != null ? dto.getEnrollmentDate() : LocalDate.now())
                .grade(dto.getGrade())
                .paid(false)
                .build();

        return enrollmentMapper.toDTO(enrollmentRepository.save(enrollment));
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(enrollmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO updateGrade(Long enrollmentId, Double grade) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Зарахування не знайдено"));

        enrollment.setGrade(grade);
        Enrollment saved = enrollmentRepository.save(enrollment);

        studentService.recalculateGPA(saved.getStudent().getId());

        return enrollmentMapper.toDTO(saved);
    }

    public EnrollmentDTO payCourse(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Зарахування не знайдено"));

        enrollment.setPaid(true);
        return enrollmentMapper.toDTO(enrollmentRepository.save(enrollment));
    }
}