package gr.techpro.absence.repository.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import gr.techpro.absence.entity.Session;

public class SessionSpecifications {

    private SessionSpecifications() {}

    public static Specification<Session> hasModuleId(Long moduleId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<Module>get("module").<Long>get("id"), moduleId);
    }

    public static Specification<Session> fromDate(LocalDate from){
        return (root, query, criteriaBuilder) -> 
               (from == null) ?  null :  criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get("sessionDate"), from);
    }
    
    public static Specification<Session> toDate(LocalDate to){
        return (root, query, criteriaBuilder) -> 
               (to == null) ? null : criteriaBuilder.lessThanOrEqualTo(root.<LocalDate>get("sessionDate"), to);
    }
}
