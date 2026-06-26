package ua.university.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.TeacherDTO;
import ua.university.sms.model.entity.Teacher;
import ua.university.sms.repository.TeacherRepository;
import ua.university.sms.exception.ResourceNotFoundException;
import ua.university.sms.mapper.TeacherMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Викладача не знайдено"));
        return teacherMapper.toDTO(teacher);
    }

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        return teacherMapper.toDTO(teacherRepository.save(teacher));
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Викладача не знайдено"));

        teacherMapper.updateTeacherFromDto(teacherDTO, teacher);
        return teacherMapper.toDTO(teacherRepository.save(teacher));
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Викладача не знайдено");
        }
        teacherRepository.deleteById(id);
    }
}