package ua.university.sms.model.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Double gpa;
}