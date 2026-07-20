package gr.techpro.absence.dto.request;

import gr.techpro.absence.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Schema(description="Payload for recording a new absence")
public record CreateAbsenceRequest(
    
    @NotNull
    @Schema(description="ID of the student's active enrollment to the module", example="54") 
    Long enrollmentId,
    
    @NotNull
    @Schema(description="The ID of the session in which the absence is recorded", example="21") 
    Long sessionId,

    @Schema(description="Student's attendance status at the session. Optional field. Default: 'ABSENT'", example="ABSENT")
    AttendanceStatus status,
    
    
    @Schema(description="""
            Indicates whether the absence is justified
            The status must be 'ABSENT' in order to justify an absence.
            Optional field. Default: 'false'
            """, example="true")
    Boolean justified,

    
    @Size(max = 255, message="The justification statement must not exceed 255 characters")
    @Schema(description="""
            A statement explaining the student's absence.
            The status must be 'ABSENT' and justified must be 'true'.
            Optional field.
            """, example="The student has COVID")
    String justification
) { }
