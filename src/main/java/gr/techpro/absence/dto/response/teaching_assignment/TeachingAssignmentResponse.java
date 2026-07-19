package gr.techpro.absence.dto.response.teaching_assignment;

import gr.techpro.absence.enums.TeachingRole;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Teaching assignment response containing the relevant identifiers and the role of assigning a module to an instructor")
public class TeachingAssignmentResponse {
    
    @Schema(description = "Teaching assignment id (surrogate key)", example = "74")
    private Long id;

    @Schema(example="102")
    private Long moduleId;

    @Schema(example="84")
    private Long instructorId;

    @Schema(description="The instructor's role in the module. Accepted values: 'LEAD', 'ASSISTANT'", example="LEAD")
    private TeachingRole role;

    public TeachingAssignmentResponse() {}

    public TeachingAssignmentResponse(Long id, Long moduleId, Long instructorId, TeachingRole role){
        this.id = id;
        this.moduleId = moduleId;
        this.instructorId = instructorId;
        this.role = role;
    }

    public Long getId() { return this.id; }
    public Long getModuleId() { return this.moduleId; }
    public Long getInstructorId() { return this.instructorId; }
    public TeachingRole getRole() { return this.role; }
}