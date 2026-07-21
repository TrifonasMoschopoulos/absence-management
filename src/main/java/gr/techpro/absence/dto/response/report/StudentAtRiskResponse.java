package gr.techpro.absence.dto.response.report;

import gr.techpro.absence.dto.response.shared.StudentSummary;
import io.swagger.v3.oas.annotations.media.Schema;

public record StudentAtRiskResponse(
    StudentSummary student,

    @Schema(description="""
            The total number of sessions in which the student's precence or absence was recorded.
            Note: Attendance is recorded only for the period during which the student's enrollment is ACTIVE.
            """, example="12")
    long totalSessions,

    @Schema(description="The number of sessions the student was absent from", example="3")
    long absent,

    @Schema(description="The student's absence rate (%)", example="25")
    double absenceRatePercent
) { }
