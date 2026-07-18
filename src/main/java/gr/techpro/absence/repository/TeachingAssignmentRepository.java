package gr.techpro.absence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gr.techpro.absence.entity.TeachingAssignment;


public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment, Long>{
    
    boolean existsByModuleIdAndInstructorId(Long moduleId, Long instructorId);
    
    
    @Query("SELECT t FROM TeachingAssignment t JOIN FETCH t.module WHERE t.instructor.id = :instructorId")
    List<TeachingAssignment> findAllByInstructorIdWithModule(@Param("instructorId") Long instructorId);
}
