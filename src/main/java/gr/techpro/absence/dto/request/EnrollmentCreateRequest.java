package gr.techpro.absence.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import gr.techpro.absence.enums.EnrollmentStatus;


@Schema(description="""
    Payload for creating a new enrollment. An enrollment assigns a student
    to a module. 
    Mandatory fields: studentId, moduleId
    Optional field: status (default=ACTIVE)
    Note: Student and Module must both exist (their id's) in order to create the enrollment
    """
)
public class EnrollmentCreateRequest {

    @NotNull
    @Schema(description="The id of an existing (registered) student", example="15")
    private Long studentId;

    @NotNull
    @Schema(description="The id of an existing module", example="12")
    private Long moduleId;

    @Schema(description="""
        The status showing the current state of this enrollment. 
        If no value given, it will be assigned to 'ACTIVE'
        Accepted values are: 
        - ACTIVE: student is currently attending the module
        - DROPPED: student dropped the module
        - COMPLETED: student successfully completed the module
    """, example="ACTIVE")
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;

    public EnrollmentCreateRequest() {}

    public Long getStudentId() { return this.studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getModuleId() { return this.moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }
    
    @JsonSetter(value="status", nulls=Nulls.SKIP)
    public void setStatus(EnrollmentStatus status) { this.status = status; }
    public EnrollmentStatus getStatus() { return this.status; }
    
}
