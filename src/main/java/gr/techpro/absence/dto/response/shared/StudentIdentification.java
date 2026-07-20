package gr.techpro.absence.dto.response.shared;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Student's identification information to easily recognize a student")
public record StudentIdentification(
    @Schema(example="5") 
    Long id,

    @Schema(description="Unique human readable number to identify a student", example="STU2025001") 
    String studentNumber,

    @Schema(example="Nikos")
    String firstName,
    
    @Schema(example="Papadakis")
    String lastName 
) {}
