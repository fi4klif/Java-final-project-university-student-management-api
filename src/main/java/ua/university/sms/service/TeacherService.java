package ua.university.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.TeacherDTO;
import ua.university.sms.model.entity.Teacher;
import ua.university.sms.repository.TeacherRepository;
import ua.university.sms.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = Teacher.builder()
                .firstName(teacherDTO.getFirstName())
                .lastName(teacherDTO.getLastName())
                .email(teacherDTO.getEmail())
                .specialization(teacherDTO.getSpecialization())
                .build();
        return mapToDTO(teacherRepository.save(teacher));
    }

    private TeacherDTO mapToDTO(Teacher teacher) {
        return TeacherDTO.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .specialization(teacher.getSpecialization())
                .build();
    }
}