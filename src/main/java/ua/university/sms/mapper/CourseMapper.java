package ua.university.sms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ua.university.sms.model.dto.CourseDTO;
import ua.university.sms.model.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "teacher.id", target = "teacherId")
    CourseDTO toDTO(Course course);

    @Mapping(target = "teacher", ignore = true)
    Course toEntity(CourseDTO dto);

    @Mapping(target = "teacher", ignore = true)
    void updateCourseFromDto(CourseDTO dto, @MappingTarget Course course);
}