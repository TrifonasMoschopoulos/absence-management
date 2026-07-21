package gr.techpro.absence.dto.response.report;

import io.swagger.v3.oas.annotations.media.Schema;

public record StudentModuleSummaryResponse(
    @Schema(description="""
            The total number of sessions in which the student's precence or absence was recorded.
            Note: Attendance is recorded only for the period during which the student's enrollment is ACTIVE.
            """, example="12") 
    long totalSessions,
    
    @Schema(description="The number of sessions attended by the student", example="9")
    long attended,

    @Schema(description="The number of sessions the student was absent from", example="3")
    long absent,

    @Schema(description="The number of excused (justified) absences", example="1")
    long justifiedAbsences,

    @Schema(description="The student's absence rate (%)", example="25")
    double absenceRatePercent
) { }
