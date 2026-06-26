package ua.university.sms.config;

import ua.university.sms.model.entity.Student;
import ua.university.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final StudentService studentService;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {

            if (studentService.count() == 0) {
                Student student1 = new Student(null, "Олександр", "Петренко", "oleksandr.p@example.com",
                        LocalDate.of(2025, 9, 1), "ACTIVE", 4.5);
                Student student2 = new Student(null, "Марія", "Коваль", "maria.k@example.com", LocalDate.of(2026, 9, 1),
                        "ACTIVE", 4.9);
                Student student3 = new Student(null, "Іван", "Сидоренко", "ivan.s@example.com",
                        LocalDate.of(2024, 9, 1), "ACADEMIC_LEAVE", 3.8);

                studentService.saveAll(List.of(student1, student2, student3));

                System.out.println("Тестових студентів успішно завантажено в базу даних!");
            } else {
                System.out.println("База даних вже містить записи.");
            }
            System.out.println("\n=========================================================");
            System.out.println("🚀 Додаток успішно запущено та готовий до роботи!");
            System.out.println("📘 Swagger UI (Документація API): http://localhost:8080/swagger-ui/index.html");
            System.out.println("📦 Список студентів (JSON):       http://localhost:8080/api/students");
            System.out.println("=========================================================\n");
        };
    }
}