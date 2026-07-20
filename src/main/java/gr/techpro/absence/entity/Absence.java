package gr.techpro.absence.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import gr.techpro.absence.enums.AttendanceStatus;
import gr.techpro.absence.exception.BusinessRuleViolationException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
    name = "absence",
    uniqueConstraints = {
        @UniqueConstraint(name="uq_absence_enrollment_session", columnNames={"enrollment_id", "session_id"})
    }
)
@EntityListeners(AuditingEntityListener.class)
public class Absence {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="enrollment_id", nullable=false)
    private Enrollment enrollment;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="session_id", nullable=false)
    private Session session;


    @Column(name="status", length=20, nullable=false)
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;


    @Column(name="justification", length=255)
    private String justification;


    @Column(name="justified", nullable=false)
    private boolean justified;


    @Column(name="recorded_at", nullable=false, updatable=false)
    @CreatedDate
    private LocalDateTime recordedAt;


    @Column(name="updated_at", nullable=false)
    @LastModifiedDate
    private LocalDateTime updatedAt;


    protected Absence() {}    

    private Absence(Builder builder){
        this.enrollment = builder.enrollment; 
        this.session = builder.session;
        this.status = builder.status; 
        this.justification = builder.justification;
        this.justified = builder.justified;
    }


    public Long getId() { return this.id; }
    public Enrollment getEnrollment() { return this.enrollment; }
    public Session getSession() { return this.session; }
    public AttendanceStatus getStatus() { return this.status; }
    public String getJustification() { return this.justification; }
    public boolean isJustified() { return this.justified; }  
    public LocalDateTime getRecordedAt() { return this.recordedAt; }
    public LocalDateTime getUpdatedAt() { return this.updatedAt; }
    

    public void updateJustification(boolean justified, String justification){
        validateJustificationRules(this.status, justified, justification);
        this.justified = justified;
        this.justification = justification;
    }

    
    public static void validateJustificationRules(AttendanceStatus status, boolean justified, String justification){
        if (justified && status != AttendanceStatus.ABSENT) 
            throw new BusinessRuleViolationException("Cannot justify an absence with a status other than 'ABSENT'");
        if (justification != null && !justification.isBlank() && !justified) 
            throw new BusinessRuleViolationException("Cannot provide justification statement to a non-justified absence");
    }


    public static class Builder {

        private Enrollment enrollment;
        private Session session;
        private AttendanceStatus status;
        private boolean justified = false;
        private String justification = null;

        public Builder(Enrollment enrollment, Session session, AttendanceStatus status){
            this.enrollment = Objects.requireNonNull(enrollment, "Enrollment cannot be null");
            this.session = Objects.requireNonNull(session, "Session cannot be null");
            this.status = Objects.requireNonNull(status, "Attendance status is mandatory");
        }

        public Builder justification(String justification){
            this.justification = justification;
            return this;
        }

        public Builder justified(Boolean justified){
            if (justified != null)
                this.justified = justified;
            return this;
        }

        public Absence build(){ 
            validateJustificationRules(this.status,this.justified, this.justification);
            return new Absence(this); 
        }
    }
}
