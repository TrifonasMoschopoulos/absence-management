package gr.techpro.absence.dto.response.teaching_assignment;

import gr.techpro.absence.dto.response.shared.ModuleSummary;
import gr.techpro.absence.enums.TeachingRole;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Intructor assignment response containing details about the assignment of instructor to a module")
public class InstructorAssignmentResponse {
    
    @Schema(description="Assignment id (surrogate key)", example="15")
    private Long assignmentId;

    @Schema(description="The instructor's role in the module. Accepted values: 'LEAD', 'ASSISTANT'", example="LEAD")
    private TeachingRole role;

    @Schema(
        description="Module summary (details)", 
        example = """
                {
                    "id": "27",
                    "code": "CS201",
                    "title": "Databases and Algorithms",
                    "semester": "SPRING",
                    "academicYear": "2025"
                }
                """                   
    )
    private ModuleSummary moduleSummary;

    public InstructorAssignmentResponse() { }

    public InstructorAssignmentResponse(Long assignmentId, TeachingRole role, ModuleSummary moduleSummary){
        this.assignmentId = assignmentId;
        this.role = role;
        this.moduleSummary = moduleSummary;
    }

    public Long getAssignmentId() { return this.assignmentId; }
    public TeachingRole getRole() { return this.role; }
    public ModuleSummary getModuleSummary() { return this.moduleSummary; }
}
