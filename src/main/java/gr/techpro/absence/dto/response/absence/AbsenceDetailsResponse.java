package gr.techpro.absence.dto.response.absence;

import java.time.LocalDateTime;

import gr.techpro.absence.dto.response.shared.SessionSummary;
import gr.techpro.absence.dto.response.shared.StudentSummary;
import gr.techpro.absence.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Absence details response containing all available information for an absence")
public record AbsenceDetailsResponse(
    @Schema(description="Absence id (surrogate key)", example="5") Long id,
    
    @Schema(description="Student's attendance status at the session. Accepted values: 'ABSENT', 'PRESENT', 'LATE'", example="LATE")
    AttendanceStatus status,

    @Schema(description="Indicates whether the absence is justified (if student status is ABSENT)", example="true")
    boolean justified,

    @Schema(description="A statement explaining the absence (if student status is ABSENT and justified)", example="The student has COVID")
    String justification,
 
    StudentSummary student,

    SessionSummary session,

    @Schema(description="The timestamp at which the absence was recorded", example="2026-05-21T19:15:25")
    LocalDateTime recordedAt,

    @Schema(description="Timestamp of the last update of the absence", example="2026-06-21T18:00:00")
    LocalDateTime updatedAt
) { }
