package ua.university.sms.repository;

import ua.university.sms.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastNameContainingIgnoreCase(String lastName);

    @Query("SELECT DISTINCT s FROM Student s JOIN Enrollment e ON s.id = e.student.id WHERE e.paid = false")
    List<Student> findUnpaidStudents();

    @Query("SELECT s FROM Student s ORDER BY s.gpa DESC")
    List<Student> findTopStudents(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE (:status IS NULL OR s.status = :status) AND (:year IS NULL OR EXTRACT(YEAR FROM s.enrollmentDate) = :year)")
    List<Student> findByStatusAndYear(@Param("status") String status, @Param("year") Integer year);

    @Query("SELECT s FROM Student s WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(s.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Student> searchByQuery(@Param("query") String query);
}