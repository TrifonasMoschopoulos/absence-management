package gr.techpro.absence.dto.response.absence;

import gr.techpro.absence.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Absence justification response containing the basic absence information")
public record UpdateJustificationResponse(
    @Schema(description="The ID of the absence (surrogate key)", example="5") Long id,
    @Schema(description="The ID of the student's active enrollment to the module", example="54") Long enrollmentId,
    @Schema(description="The ID of the session in which the absence is recorded", example="21") Long sessionId,
    @Schema(description="Student's attendance status at the session. Accepted values: 'ABSENT', 'PRESENT', 'LATE'", example="LATE")
    AttendanceStatus status,
    @Schema(description="""
            Indicates whether the absence is justified
            The status must be 'ABSENT' in order to justify an absence.
            """, example="true")
    boolean justified,
    
    @Schema(description="A statement explaining the absence (if student status is ABSENT and justified)", example="The student has COVID")
    String justification
) { }
