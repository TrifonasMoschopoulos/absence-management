package gr.techpro.absence.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import gr.techpro.absence.entity.Absence;
import gr.techpro.absence.entity.Enrollment;
import gr.techpro.absence.entity.Session;
import gr.techpro.absence.entity.Student;
import gr.techpro.absence.entity.Module;

import jakarta.persistence.criteria.Join;

public class AbsenceSpecifications {

    private AbsenceSpecifications() {}
    
    public static Specification<Absence> hasSessionId(Long sessionId){
        return (root, query, builder) -> {
            if (sessionId == null)
                return null;
            
            return builder.equal(root.<Session>get("session").<Long>get("id"), sessionId);
        };
    }

    public static Specification<Absence> hasModuleId(Long moduleId){
        return (root, query, builder) -> {
            if (moduleId == null)
                return null;
            
            Join<Absence, Session> sessionJoin = root.join("session");
            return builder.equal(sessionJoin.<Module>get("module").get("id"), moduleId);
        };
    }

    public static Specification<Absence> hasStudentId(Long studentId){
        return (root, query, builder) ->{
            if (studentId == null)
                return null;

            Join<Absence, Enrollment> enrollmentJoin = root.join("enrollment");
            return builder.equal(enrollmentJoin.<Student>get("student").<Long>get("id"), studentId);
        };
    }
}
