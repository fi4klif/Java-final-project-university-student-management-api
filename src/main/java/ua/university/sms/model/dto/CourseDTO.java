package ua.university.sms.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Long teacherId;
}