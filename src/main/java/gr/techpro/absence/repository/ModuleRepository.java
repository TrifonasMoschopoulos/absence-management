package gr.techpro.absence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gr.techpro.absence.entity.Module;
import gr.techpro.absence.enums.Semester;

public interface ModuleRepository extends JpaRepository<Module, Long>{

    @Query("SELECT m FROM Module m WHERE ((:title is NULL or m.title=:title) and (:credits is NULL or m.credits=:credits) and (:semester is NULL or m.semester=:semester) and (:academicYear is NULL or m.academicYear=:academicYear))")
    List<Module> searchModules(@Param("title") String title, @Param("credits") Integer credits, @Param("semester") Semester semester, @Param("academicYear") Integer academicYear);
    
}
