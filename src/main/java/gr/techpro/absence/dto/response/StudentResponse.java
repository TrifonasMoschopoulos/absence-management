package gr.techpro.absence.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student details response containing all available information about a student.")
public class StudentResponse {
    
    @Schema(description = "Surrogate key.", example = "1")
    private Long id;
    
    @Schema(example = "Johnny")
    private String firstName;
    
    @Schema(example = "Depp")
    private String lastName;

    @Schema(example = "johnny.depp@gmail.com")
    private String email;

    @Schema(description = "Unique identifier. Format: 'STU' + YYYY (Year) + at least 3-digit sequence", example = "STU2025001")
    private String studentNumber;

    @Schema(description = "Student's enrollment date.", example = "2025-09-91")
    private LocalDate enrollmentDate;

    public StudentResponse() { }

    public StudentResponse(Long id, String firstName, String lastName, String email, String studentNumber, LocalDate enrollmentDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentNumber = studentNumber;
        this.enrollmentDate = enrollmentDate;
    }

    public Long getId() { return this.id; }
    public String getFirstName(){ return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getEmail() { return this.email; }
    public String getStudentNumber() { return this.studentNumber; }
    public LocalDate getEnrollmentDate() { return this.enrollmentDate; }
    
}
