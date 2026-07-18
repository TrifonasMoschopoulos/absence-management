package gr.techpro.absence.dto.response;

import java.time.LocalDate;

import gr.techpro.absence.enums.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description="Enrollment response for sharing available information about an enrollment of a student to a module")
public class EnrollmentResponse {
    
    @Schema(description="Enrollment id (surrogate key)", example="55")
    private Long id;

    @Schema(description="""
        The status showing the current state of this enrollment. 
        Accepted values are: 
        - ACTIVE: student is currently attending the module
        - DROPPED: student dropped the module
        - COMPLETED: student successfully completed the module
    """, example="ACTIVE")
    private EnrollmentStatus status;

    @Schema(description="The enrollment date", example="2025-09-25")
    private LocalDate enrolledAt;
    
    @Schema(description="The student to whom this enrollment concerns")
    private StudentSummary student;

    @Schema(description="The module this enrollment concerns")
    private ModuleSummary module;

    // NESTED public records used as DTOs for student and module information
    public record StudentSummary(
        @Schema(example="15") Long id, 
        @Schema(description="Human readable Unique identifier for student.", example="STU2025001") String studentNumber, 
        @Schema(example="Eleni") String firstName, 
        @Schema(example="Vasiliou") String lastName, 
        @Schema(example="e.vasiliou@students.techpro.gr") String email
    ) {}

    
    
    public EnrollmentResponse() {}
    
    public EnrollmentResponse(Long id, EnrollmentStatus status, LocalDate enrolledAt, StudentSummary student, ModuleSummary module){
        this.id = id;
        this.status = status;
        this.enrolledAt = enrolledAt;
        this.student = student;
        this.module = module;
    }

    
    public Long getId() { return this.id; }
    public EnrollmentStatus getStatus() { return this.status; }
    public LocalDate getEnrolledAt() { return this.enrolledAt; }
    public StudentSummary getStudent() { return this.student; }
    public ModuleSummary getModule() { return this.module; }
}
