package ua.university.sms.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialization;
}