package ua.university.sms.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
    private Double grade;
    private boolean paid;
}