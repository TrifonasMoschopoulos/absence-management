package gr.techpro.absence.dto.response.shared;

import io.swagger.v3.oas.annotations.media.Schema;

public record StudentSummary(
        @Schema(example="15") Long id, 
        @Schema(description="Human readable Unique identifier for student.", example="STU2025001") String studentNumber, 
        @Schema(example="Eleni") String firstName, 
        @Schema(example="Vasiliou") String lastName, 
        @Schema(example="e.vasiliou@students.techpro.gr") String email
    ) {}
