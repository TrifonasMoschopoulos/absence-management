package gr.techpro.absence.dto.response.absence;

import gr.techpro.absence.dto.response.shared.SessionSummary;
import gr.techpro.absence.dto.response.shared.StudentIdentification;
import gr.techpro.absence.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;



@Schema(description="Absence summary response containing a summary for an absence")
public record AbsenceSummaryResponse(
    
    @Schema(description="Absence id (surrogate key)", example="5") Long id,
    
    @Schema(description="Student's attendance status at the session. Accepted values: 'ABSENT', 'PRESENT', 'LATE'", example="LATE")
    AttendanceStatus status,

    @Schema(description="Indicates whether the absence is justified (if student status is ABSENT)", example="true")
    boolean justified,

    StudentIdentification student,

    SessionSummary session
)
{}
