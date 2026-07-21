package gr.techpro.absence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.techpro.absence.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
    
    boolean existsByStudentIdAndModuleId(Long studentId, Long moduleId);
    
    Optional<Enrollment> findByStudentIdAndModuleId(Long studentId, Long moduleId);
}
