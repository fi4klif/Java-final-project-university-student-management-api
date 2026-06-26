package ua.university.sms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import ua.university.sms.model.dto.CourseDTO;
import ua.university.sms.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAll(@RequestParam(required = false) Long teacherId,
            @RequestParam(required = false) Integer credits) {
        return courseService.getCourses(teacherId, credits);
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@Valid @RequestBody CourseDTO dto) {
        return courseService.createCourse(dto);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable Long id, @Valid @RequestBody CourseDTO dto) {
        return courseService.updateCourse(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/avg-gpa")
    public Double getAvgGpa(@PathVariable Long id) {
        return courseService.getAverageGpa(id);
    }
}