package ua.university.sms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.university.sms.model.dto.TeacherDTO;
import ua.university.sms.service.TeacherService;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherDTO> getAll() {
        return teacherService.getAllTeachers();
    }

    @PostMapping
    public TeacherDTO create(@RequestBody TeacherDTO dto) {
        return teacherService.createTeacher(dto);
    }
}