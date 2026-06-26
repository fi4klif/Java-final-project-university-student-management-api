package ua.university.sms.model.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// StudentDTO.java
@Data
public class StudentDTO {
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;
    @Email(message = "Invalid email format")
    private String email;
    private Double gpa;
}