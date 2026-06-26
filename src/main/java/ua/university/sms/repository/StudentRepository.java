package ua.university.sms.repository;

import ua.university.sms.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastNameContainingIgnoreCase(String lastName);

    @Query("SELECT DISTINCT s FROM Student s JOIN Enrollment e ON s.id = e.student.id WHERE e.paid = false")
    List<Student> findUnpaidStudents();

    @Query("SELECT s FROM Student s ORDER BY s.gpa DESC")
    List<Student> findTopStudents(Pageable pageable);
}
