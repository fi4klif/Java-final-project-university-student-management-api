package ua.university.sms.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.university.sms.model.dto.CourseDTO;
import ua.university.sms.model.entity.Course;
import ua.university.sms.model.entity.Teacher;
import ua.university.sms.repository.CourseRepository;
import ua.university.sms.repository.TeacherRepository;
import ua.university.sms.mapper.CourseMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    @Test
    void createCourse_ShouldReturnSavedCourse() {
        CourseDTO inputDto = CourseDTO.builder().title("Math").teacherId(1L).build();
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        Course course = new Course();
        course.setTitle("Math");
        course.setTeacher(teacher);
        CourseDTO outputDto = CourseDTO.builder().title("Math").teacherId(1L).build();

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(courseMapper.toEntity(inputDto)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toDTO(course)).thenReturn(outputDto);

        CourseDTO result = courseService.createCourse(inputDto);

        assertNotNull(result);
        assertEquals("Math", result.getTitle());
        assertEquals(1L, result.getTeacherId());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void getAverageGpa_ShouldReturnCorrectValue() {
        Long courseId = 1L;
        Double expectedGpa = 4.8;

        when(courseRepository.getAverageGradeByCourseId(courseId)).thenReturn(expectedGpa);

        Double result = courseService.getAverageGpa(courseId);

        assertEquals(expectedGpa, result);
        verify(courseRepository, times(1)).getAverageGradeByCourseId(courseId);
    }
}