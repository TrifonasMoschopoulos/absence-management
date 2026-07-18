package gr.techpro.absence.dto.request;

import gr.techpro.absence.enums.TeachingRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


@Schema(description="""
    Payload to assign an instructor to a module. 
    Every instructor assigned to a module has a ROLE which can be 'LEAD' or 'ASSISTANT'.
    An instructor can be assigned to multiple modules and a module can be taught by many instructors.;
    Note: The 'role' field is optional (default='LEAD')
"""
)
public class TeachingAssignmentCreateRequest {
    
    @NotNull
    @Schema(description = "The module's id (surrogate key)", example="12")
    private Long moduleId;
    
    @NotNull
    @Schema(description = "The instructor's id (surrogate key)", example="25")
    private Long instructorId;

    @Schema(description = """
        The instructor's role in the module.
        If no value given, it will be assigned to 'LEAD'.
        Accepted values are: 'LEAD', 'ASSISTANT'
    """, example="ASSISTANT"
    )
    private TeachingRole role;

    public TeachingAssignmentCreateRequest() {}

    public Long getModuleId() { return this.moduleId; }
    public Long getInstructorId() { return this.instructorId; }
    public TeachingRole getRole() { return this.role; }

    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }
    public void setRole(TeachingRole role) { this.role = role; }
}
