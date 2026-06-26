package ua.university.sms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.university.sms.model.dto.EnrollmentDTO;
import ua.university.sms.service.EnrollmentService;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public EnrollmentDTO enroll(@RequestBody EnrollmentDTO dto) {
        return enrollmentService.enrollStudent(dto);
    }

    @GetMapping
    public List<EnrollmentDTO> getAll() {
        return enrollmentService.getAllEnrollments();
    }

    @PatchMapping("/{id}/grade")
    public EnrollmentDTO updateGrade(@PathVariable Long id, @RequestParam Double grade) {
        return enrollmentService.updateGrade(id, grade);
    }

    @PatchMapping("/{id}/pay")
    public EnrollmentDTO pay(@PathVariable Long id) {
        return enrollmentService.payCourse(id);
    }
}