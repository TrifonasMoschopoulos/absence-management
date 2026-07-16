package gr.techpro.absence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import gr.techpro.absence.entity.Session;


public interface SessionRepository extends JpaRepository<Session, Long>, JpaSpecificationExecutor<Session> {
    
}
