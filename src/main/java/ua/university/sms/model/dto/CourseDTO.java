package ua.university.sms.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDTO {
    private Long id;
    private String title;
    private String code;
    private Integer credits;
    private String description;
    private Long teacherId;
}