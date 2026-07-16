package gr.techpro.absence.api;

import java.time.LocalDate;
import java.util.List;

import gr.techpro.absence.dto.request.SessionCreateRequest;
import gr.techpro.absence.dto.response.SessionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name="Session Management", description="Endpoints to access and create sessions for a module")
public interface SessionApi {
    

    @Operation(summary="Retrieve the sessions of a Module",
        description="""
        Access the sessions of a module by their module ID (surrogate key). 
        It also provides optional dating filters to narrow the search. 
        """
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Sessions retrieved successfully"),
        @ApiResponse(responseCode="400", description="Invalid values for parameters 'from'/'to'"),
        @ApiResponse(responseCode="404", description="Module with the provided ID not found"),
        @ApiResponse(responseCode="422", description="Date 'from' incorrectly occurs chronologically after Date 'to'")
    })
    List<SessionResponse> getSessions(Long moduleId, LocalDate from, LocalDate to);



    @Operation(summary="Create a new session", 
    description="""
    Create a new session for an existing module.
    The provided module ID must belong to an existing module.
    All parameters are required except from 'topic' which is optional  
    """       
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Session created successfully"),
        @ApiResponse(responseCode="400", description="Invalid field values OR missing required fields"),
        @ApiResponse(responseCode="404", description="Module with the provided ID not found"),
        @ApiResponse(responseCode="422", description="Session's start time incorrectly doesn't precede session's end time")
    })
    SessionResponse createSession(Long moduleId, @Valid SessionCreateRequest request);
}
