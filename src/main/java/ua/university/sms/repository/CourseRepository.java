package ua.university.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import ua.university.sms.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT AVG(e.grade) FROM Enrollment e WHERE e.course.id = :courseId")
    Double getAverageGradeByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT c FROM Course c WHERE (:teacherId IS NULL OR c.teacher.id = :teacherId) AND (:credits IS NULL OR c.credits = :credits)")
    List<Course> filterCourses(@Param("teacherId") Long teacherId, @Param("credits") Integer credits);
}