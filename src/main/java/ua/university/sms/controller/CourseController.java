package ua.university.sms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.university.sms.model.dto.CourseDTO;
import ua.university.sms.service.CourseService;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAll() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public CourseDTO create(@RequestBody CourseDTO dto) {
        return courseService.createCourse(dto);
    }
}