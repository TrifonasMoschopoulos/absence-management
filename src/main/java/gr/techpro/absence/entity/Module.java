package gr.techpro.absence.entity;

import java.time.LocalDateTime;

import gr.techpro.absence.enums.Semester;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Module {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="code", length=20, nullable=false, unique=true)
    private String code;

    @Column(name="title", length=255, nullable=false)
    private String title;

    @Column(name="credits", nullable=false)
    private int credits;

    @Column(name="semester", length=20, nullable=false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(name="academic_year", nullable=false)
    private int academicYear;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt = LocalDateTime.now();


    public Module() { }

    public Module(String code, String title, int credits, Semester semester, int academicYear){
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    public Long getId() { return this.id; }

    public String getCode() { return this.code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title;}

    public int getCredits() { return this.credits; }
    public void setCredits(int credits) { this.credits = credits; }
    
    public Semester getSemester() { return this.semester; }
    public void setSemester(Semester semester) { this.semester = semester; }

    public int getAcademicYear() { return this.academicYear; }
    public void setAcademicYear(int academicYear) { this.academicYear = academicYear; }

    public LocalDateTime getCreatedAt() { return this.createdAt; }
}
