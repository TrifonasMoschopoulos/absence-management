package gr.techpro.absence.dto.response;

import gr.techpro.absence.enums.Semester;
import io.swagger.v3.oas.annotations.media.Schema;

public record ModuleSummary(
        @Schema(example="12") Long id, 
        @Schema(description="Human readable Unique identifier for module.", example="CS201") String code, 
        @Schema(description="Course title", example="Database Systems") String title, 
        @Schema(description="The academic semester in which the course takes place",  example="SPRING") Semester semester, 
        @Schema(example="2026") int academicYear
    ) { }
