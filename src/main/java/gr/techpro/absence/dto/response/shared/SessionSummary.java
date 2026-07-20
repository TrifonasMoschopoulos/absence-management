package gr.techpro.absence.dto.response.shared;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public record SessionSummary(
    @Schema(example="20") 
    Long id, 
    
    @Schema(description="Date of the session", example="2025-05-13") 
    LocalDate sessionDate, 
    
    @Schema(description="The ID of the course (module) to which the session belongs", example="12")
    Long moduleId,

    @Schema(description="Unique human readable code to identify a module", example="CS101")
    String moduleCode,

    @Schema(description="The title of the course (module) to which the session belongs", example="Intro to databases & SQL")
    String moduleTitle
) {}
