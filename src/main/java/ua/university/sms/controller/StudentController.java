package ua.university.sms.controller;

import ua.university.sms.model.dto.StudentDTO;
import ua.university.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAll(@RequestParam(required = false) String status,
            @RequestParam(required = false) Integer year) {
        return studentService.getStudents(status, year);
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentDTO create(@Valid @RequestBody StudentDTO dto) {
        return studentService.createStudent(dto);
    }

    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable Long id, @Valid @RequestBody StudentDTO dto) {
        return studentService.updateStudent(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unpaid")
    public List<StudentDTO> getUnpaid() {
        return studentService.getUnpaidStudents();
    }

    @GetMapping("/top")
    public List<StudentDTO> getTop(@RequestParam int limit) {
        return studentService.getTopStudents(limit);
    }

    @GetMapping("/search")
    public List<StudentDTO> search(@RequestParam String query) {
        return studentService.searchStudents(query);
    }
}