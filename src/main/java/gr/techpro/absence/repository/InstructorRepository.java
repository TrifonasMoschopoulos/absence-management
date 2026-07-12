package gr.techpro.absence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gr.techpro.absence.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>{
    
    boolean existsByEmail(String email);

    @Query("SELECT i FROM Instructor i WHERE ((:firstName is NULL or i.firstName=:firstName) and (:lastName is NULL or i.lastName = :lastName))")
    List<Instructor> searchInstructors(@Param("firstName") String firstName, @Param("lastName") String lastName);


}
