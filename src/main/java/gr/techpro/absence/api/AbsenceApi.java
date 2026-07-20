package gr.techpro.absence.api;

import java.util.List;

import gr.techpro.absence.dto.request.CreateAbsenceRequest;
import gr.techpro.absence.dto.request.UpdateJustificationRequest;
import gr.techpro.absence.dto.response.absence.AbsenceDetailsResponse;
import gr.techpro.absence.dto.response.absence.AbsenceSummaryResponse;
import gr.techpro.absence.dto.response.absence.CreateAbsenceResponse;
import gr.techpro.absence.dto.response.absence.UpdateJustificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Absence Management",
    description="Endpoints to register, update, justify and access absences"
)
public interface AbsenceApi {

    @Operation(summary="Retrieve an absence",
        description="Get an existing absence by its ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Absence successfully retrieved"),
        @ApiResponse(responseCode="404", description="Absence with the provided ID not found", content=@Content)
    })
    AbsenceDetailsResponse getAbsence(Long id);


    @Operation(summary="Retrieves absences using filters",
        description="Retrieves the absences that match with the filters given. At least one filter must be provided."
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Absences successfully retrieved"),
        @ApiResponse(responseCode="400", description="No filter was provided", content=@Content),
        @ApiResponse(responseCode="404", description="One of the provided IDs corresponds to a non-existent resource", content=@Content)
    })
    List<AbsenceSummaryResponse> filterAbsences(Long studentId, Long moduleId, Long sessionId);


    @Operation(summary="Create a new absence",
        description="Records a student's absence from a session"
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="The absence was successfully recorded"),
        @ApiResponse(responseCode="400", description="Invalid JSON format or invalid field", content=@Content),
        @ApiResponse(responseCode="404", description="One of the provided IDs corresponds to a non-existent resource", content=@Content),
        @ApiResponse(responseCode="409", description="There is already an absence recorded for the provided session and enrollment IDs", content=@Content),
        @ApiResponse(responseCode="422", description="""
                    - Enrollment with the provided id is not ACTIVE
                    - The student associated with the provided enrollment ID is not enrolled in the module for the specific session
                    - Provided field 'justified' has value 'true' but field 'status' is not 'ABSENT'
                    - A justification provided but the field 'justified' is false
                """, content=@Content)
    })
    CreateAbsenceResponse createAbsence(@Valid CreateAbsenceRequest request);



    @Operation(summary="Justify or Unjustify an absence",
        description="It marks an existing absence as justified or unjustified."
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="The absence was successfully updated"),
        @ApiResponse(responseCode="400", description="Invalid JSON format or invalid field", content=@Content),
        @ApiResponse(responseCode="404", description="Absence with the provided ID not found", content=@Content),
        @ApiResponse(responseCode="422", description="""
                    - Provided field 'justified' has value 'true' but field 'status' is not 'ABSENT'
                    - A justification provided but the field 'justified' is false
                """, content=@Content)
    })
    UpdateJustificationResponse updateAbsenceJustification(Long id, @Valid UpdateJustificationRequest request);

}
