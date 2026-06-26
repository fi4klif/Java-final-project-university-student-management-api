Markdown

# University Student Management API

## English Version

### Description

This project is a robust RESTful API backend for a University Management System, developed using **Spring Boot 3**. It provides a comprehensive solution for managing academic data, including students, teachers, courses, and the enrollment process. It features automated GPA calculation and advanced search capabilities.

### Key Features

- **CRUD Operations:** Full management for Students, Teachers, and Courses.
- **Enrollment Management:** Course registration, grade assignment, and payment tracking.
- **Business Logic:** Automatic GPA calculation upon grade updates.
- **Advanced Searching:** Filter students by status, academic year, or search by name/email.
- **API Documentation:** Interactive Swagger UI for easy endpoint testing.

### Tech Stack

- **Java 21**, **Spring Boot 3.3.4**
- **PostgreSQL** (Database), **Spring Data JPA**
- **MapStruct** (Mapping), **Lombok** (Boilerplate)
- **JUnit 5 & Mockito** (Testing)

### Getting Started

1. **Database:** Ensure PostgreSQL is running and create a database named `university_db`.
2. **Configuration:** Update `src/main/resources/application.yml` with your database credentials.
3. **Run:** Execute the following command in your terminal:
   ```bash
   ./mvnw spring-boot:run
   ```
   Docs: Access Swagger UI at http://localhost:8080/swagger-ui/index.html.

## Українська версія

### Опис

Цей проєкт — це потужний бекенд-застосунок RESTful API для системи управління університетом, розроблений на базі Spring Boot 3. Він пропонує комплексне рішення для управління академічними даними, включаючи студентів, викладачів, курси та процес зарахування. Система підтримує автоматичний розрахунок середнього балу (GPA) та має розширені можливості пошуку.

### Основні функції

- CRUD операції: Повне управління студентами, викладачами та курсами.
- Управління зарахуванням: Реєстрація на курси, виставлення оцінок та фіксація оплати.
- Бізнес-логіка: Автоматичний перерахунок GPA при зміні оцінок.
- Розширений пошук: Фільтрація студентів за статусом, роком навчання, або пошук за іменем/email.
- Документація API: Інтерактивний Swagger UI для зручного тестування ендпоінтів.

### Технічний стек

- Java 21, Spring Boot 3.3.4
- PostgreSQL (База даних), Spring Data JPA
- MapStruct (Мапінг), Lombok (Зменшення коду)
- JUnit 5 та Mockito (Тестування)

### Початок роботи

1. База даних: Переконайтеся, що PostgreSQL запущено, та створіть базу даних university_db.
2. Налаштування: Оновіть src/main/resources/application.yml вашими даними для підключення до БД.
3. Запуск: Виконайте команду в терміналі:

```Bash
./mvnw spring-boot:run
```

Документація: Перейдіть за посиланням: http://localhost:8080/swagger-ui/index.html, щоб протестувати API.
