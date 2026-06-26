package ua.university.sms.controller;

import ua.university.sms.model.dto.StudentDTO;
import ua.university.sms.model.entity.Student;
import ua.university.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentDTO(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return studentService.convertToDTO(student);
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Студента з ID " + id + " успішно видалено.";
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String lastName) {
        return studentService.searchStudentsByLastName(lastName);
    }
}