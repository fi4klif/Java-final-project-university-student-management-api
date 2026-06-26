package ua.university.sms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.university.sms.model.dto.EnrollmentDTO;
import ua.university.sms.model.entity.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    EnrollmentDTO toDTO(Enrollment enrollment);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    Enrollment toEntity(EnrollmentDTO dto);
}