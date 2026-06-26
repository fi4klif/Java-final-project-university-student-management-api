package ua.university.sms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ua.university.sms.model.dto.StudentDTO;
import ua.university.sms.model.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    @Mapping(target = "enrollmentDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    Student toEntity(StudentDTO dto);

    @Mapping(target = "enrollmentDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateStudentFromDto(StudentDTO dto, @MappingTarget Student student);
}