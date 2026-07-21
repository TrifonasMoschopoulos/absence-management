package gr.techpro.absence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gr.techpro.absence.entity.Absence;
import io.micrometer.common.lang.Nullable;


public interface AbsenceRepository extends JpaRepository<Absence, Long>, JpaSpecificationExecutor<Absence>{

    @Query("""
        SELECT a FROM Absence a
        JOIN FETCH a.enrollment e
        JOIN FETCH a.session s
        JOIN FETCH e.student
        JOIN FETCH s.module
        WHERE a.id = :id
        """)
    public Optional<Absence> findByIdWithDetails(@Param("id") Long id);
 

    public boolean existsBySessionIdAndEnrollmentId(Long sessionId, Long enrollmentId);

    public List<Absence> findByEnrollmentId(Long enrollmentId);

    @Query(
        """
        SELECT a FROM Absence a 
        JOIN FETCH a.enrollment e 
        JOIN FETCH e.student 
        WHERE e.module.id = :moduleId
    """)
    public List<Absence> findAllAbsencesWithStudentByModuleId(@Param("moduleId") Long moduleId);
    

    @EntityGraph(attributePaths={"enrollment.student", "session.module"})
    public List<Absence> findAll(@Nullable Specification<Absence> spec, Sort sort);
}
