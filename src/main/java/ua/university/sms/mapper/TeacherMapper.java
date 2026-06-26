package ua.university.sms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ua.university.sms.model.dto.TeacherDTO;
import ua.university.sms.model.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDTO toDTO(Teacher teacher);

    @Mapping(target = "courses", ignore = true)
    Teacher toEntity(TeacherDTO dto);

    @Mapping(target = "courses", ignore = true)
    void updateTeacherFromDto(TeacherDTO dto, @MappingTarget Teacher teacher);
}