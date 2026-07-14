package gr.techpro.absence.entity;

import java.time.LocalDate;

import gr.techpro.absence.enums.EnrollmentStatus;
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
@Table(uniqueConstraints={ @UniqueConstraint(name="uq_enrollment_student_module", columnNames={"student_id", "module_id"})})
public class Enrollment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="student_id", nullable=false)
    private Student student;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="module_id", nullable=false)
    private Module module;

    @Column(name="enrolled_at", nullable=false)
    private LocalDate enrolledAt = LocalDate.now();

    @Column(name="status", length=20, nullable=false)
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;


    public Enrollment() {}

    public Enrollment(Student student, Module module, EnrollmentStatus status){
        this.student = student;
        this.module = module;
        this.status = status;
    }

    public Long getId() { return this.id; }
    public Student getStudent() { return this.student; }
    public Module getModule() { return this.module; }
    public LocalDate getEnrolledAt() { return this.enrolledAt; }
    
    public EnrollmentStatus getStatus() { return this.status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }

}
