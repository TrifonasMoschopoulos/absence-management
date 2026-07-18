package gr.techpro.absence.entity;

import gr.techpro.absence.enums.TeachingRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name="module_instructor",
    uniqueConstraints = { 
        @UniqueConstraint(name="uq_module_instuctor", columnNames={"module_id", "instructor_id"})
    }
)
public class TeachingAssignment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="module_id", nullable=false)
    private Module module;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="instructor_id", nullable=false)
    private Instructor instructor;


    @Column(name="role", length=50, nullable=false)
    @Enumerated(EnumType.STRING)
    private TeachingRole role = TeachingRole.LEAD;


    public TeachingAssignment() {}
    
    public TeachingAssignment(Module module, Instructor instructor){
        this.module = module;
        this.instructor = instructor;
    }

    public TeachingAssignment(Module module, Instructor instructor, TeachingRole role){
        this(module, instructor);
        this.role = role;
    }

    public Long getId() { return this.id; }
    public Module getModule() { return this.module; }
    public Instructor getInstructor() { return this.instructor; }
    public TeachingRole getRole() { return this.role; }


    public void setRole(TeachingRole role) { this.role = role; }
}
