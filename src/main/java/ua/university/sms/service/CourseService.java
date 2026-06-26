package ua.university.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.CourseDTO;
import ua.university.sms.model.entity.Course;
import ua.university.sms.model.entity.Teacher;
import ua.university.sms.repository.CourseRepository;
import ua.university.sms.repository.TeacherRepository;
import ua.university.sms.exception.ResourceNotFoundException;
import ua.university.sms.mapper.CourseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Курс не знайдений"));
        return courseMapper.toDTO(course);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Teacher teacher = null;
        if (courseDTO.getTeacherId() != null) {
            teacher = teacherRepository.findById(courseDTO.getTeacherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Викладач не знайдений"));
        }

        Course course = courseMapper.toEntity(courseDTO);
        course.setTeacher(teacher);
        return courseMapper.toDTO(courseRepository.save(course));
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Курс не знайдений"));

        Teacher teacher = null;
        if (courseDTO.getTeacherId() != null) {
            teacher = teacherRepository.findById(courseDTO.getTeacherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Викладач не знайдений"));
        }

        courseMapper.updateCourseFromDto(courseDTO, course);
        course.setTeacher(teacher);
        return courseMapper.toDTO(courseRepository.save(course));
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Курс не знайдений");
        }
        courseRepository.deleteById(id);
    }

    public Double getAverageGpa(Long courseId) {
        return courseRepository.getAverageGradeByCourseId(courseId);
    }
}