package ua.university.sms.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ім'я не може бути порожнім")
    @Size(min = 2, max = 50, message = "Ім'я має містити від 2 до 50 символів")
    private String firstName;

    @NotBlank(message = "Прізвище не може бути порожнім")
    private String lastName;

    @Email(message = "Некоректний формат електронної пошти")
    @NotBlank(message = "Email є обов'язковим")
    private String email;

    private LocalDate enrollmentDate;

    @Pattern(regexp = "^(ACTIVE|GRADUATED|ACADEMIC_LEAVE)$", message = "Невідомий статус")
    private String status;

    @DecimalMin(value = "0.0", message = "Мінімальний бал 0.0")
    @DecimalMax(value = "10.0", message = "Максимальний бал 10.0")
    private Double gpa;
}