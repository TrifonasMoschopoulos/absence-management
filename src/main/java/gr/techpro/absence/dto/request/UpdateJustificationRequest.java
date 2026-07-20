package gr.techpro.absence.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Schema(description="Payload to justify or unjustify an absence")
public record UpdateJustificationRequest(
    @NotNull
    @Schema(description="""
            Indicates whether the absence is justified
            The status must be 'ABSENT' in order to justify an absence.
            """, example="true")
    Boolean justified,

    @Size(max = 255, message="The justification statement must not exceed 255 characters")
    @Schema(description="""
            A statement explaining the student's absence.
            The status must be 'ABSENT' and justified must be 'true'.
            Optional field
            """, example="The student has COVID")
    String justification
) {}
