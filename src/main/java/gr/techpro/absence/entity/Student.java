package gr.techpro.absence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "student_number", length = 50, nullable = false, unique = true)
    private String studentNumber;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate = LocalDate.now();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt= LocalDateTime.now();


    public Student(){ }

    public Student(String firstName, String lastName, String email, String studentNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentNumber = studentNumber;
    }

    public Long getId() { return this.id; }

    public String getFirstName(){ return this.firstName; }
    public void setFirstName(String firstName){ this.firstName = firstName;  }

    public String getLastName(){ return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail(){ return this.email; }
    public void setEmail(String email) { this.email = email; }

    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
    public String getStudentNumber() { return this.studentNumber; }

    public LocalDate getEnrollmentDate() { return this.enrollmentDate; }

    public LocalDateTime getCreatedAt() { return this.createdAt; }

}
