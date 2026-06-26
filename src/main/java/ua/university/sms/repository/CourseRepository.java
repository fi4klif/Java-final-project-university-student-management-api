package ua.university.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.university.sms.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT AVG(e.grade) FROM Enrollment e WHERE e.course.id = :courseId")
    Double getAverageGradeByCourseId(@Param("courseId") Long courseId);
}