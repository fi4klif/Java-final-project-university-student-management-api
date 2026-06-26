package ua.university.sms.service;

import ua.university.sms.model.dto.StudentDTO;
import ua.university.sms.model.entity.Enrollment;
import ua.university.sms.model.entity.Student;
import ua.university.sms.repository.EnrollmentRepository;
import ua.university.sms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.university.sms.exception.ResourceNotFoundException;
import ua.university.sms.mapper.StudentMapper;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final EnrollmentRepository enrollmentRepository;

    public StudentDTO createStudent(StudentDTO dto) {
        Student student = studentMapper.toEntity(dto);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public List<StudentDTO> getStudents(String status, Integer year) {
        if (status != null || year != null) {
            return studentRepository.findByStatusAndYear(status, year).stream()
                    .map(studentMapper::toDTO)
                    .toList();
        }
        return studentRepository.findAll().stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Студента не знайдено"));
        return studentMapper.toDTO(student);
    }

    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Студента не знайдено"));

        studentMapper.updateStudentFromDto(dto, student);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public long count() {
        return studentRepository.count();
    }

    public void saveAllEntities(List<Student> students) {
        studentRepository.saveAll(students);
    }

    public void saveAll(List<Student> students) {
        studentRepository.saveAll(students);
    }

    public void recalculateGPA(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Студент не знайдений"));

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        double avgGpa = enrollments.stream()
                .filter(e -> e.getGrade() != null)
                .mapToDouble(Enrollment::getGrade)
                .average()
                .orElse(0.0);

        student.setGpa(avgGpa);
        studentRepository.save(student);
    }

    public List<StudentDTO> getUnpaidStudents() {
        return studentRepository.findUnpaidStudents().stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    public List<StudentDTO> getTopStudents(int limit) {
        return studentRepository.findTopStudents(PageRequest.of(0, limit)).stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    public List<StudentDTO> searchStudents(String query) {
        return studentRepository.searchByQuery(query).stream()
                .map(studentMapper::toDTO)
                .toList();
    }
}