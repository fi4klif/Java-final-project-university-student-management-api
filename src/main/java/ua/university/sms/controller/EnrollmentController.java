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
}