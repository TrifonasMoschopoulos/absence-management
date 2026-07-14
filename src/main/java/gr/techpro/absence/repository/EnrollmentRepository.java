package gr.techpro.absence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.techpro.absence.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
    
    boolean existsByStudentIdAndModuleId(Long studentId, Long moduleId);
}
