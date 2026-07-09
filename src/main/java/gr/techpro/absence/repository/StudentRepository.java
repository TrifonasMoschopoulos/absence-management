package gr.techpro.absence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gr.techpro.absence.entity.Student;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);
    
    @Query(" SELECT s FROM Student s WHERE (:firstName is null or s.firstName=:firstName) and (:lastName is null or s.lastName=:lastName)")
    List<Student> searchStudents(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT s.studentNumber FROM Student s WHERE s.studentNumber LIKE CONCAT(:prefix, '%') ORDER BY s.studentNumber DESC LIMIT 1")
    String findMaxStudentNumberByPrefix(@Param("prefix") String prefix);
}
