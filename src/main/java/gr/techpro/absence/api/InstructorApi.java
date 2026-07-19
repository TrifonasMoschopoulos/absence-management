package gr.techpro.absence.api;

import java.util.List;

import gr.techpro.absence.dto.request.InstructorCreateRequest;
import gr.techpro.absence.dto.response.instructor.InstructorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@Tag(name = "Instructor Management", description = "Endpoints for creating and retrieving instructors")
public interface InstructorApi {


    @Operation(summary="Retrieves an instructor",
        description="Retrieves an existing instructor based on their id (surrogate key)"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Instructor retrieved successfully"),
        @ApiResponse(responseCode="404", description="Instructor with given id doesn't exist", content=@Content)
    })
    InstructorResponse getInstructor(Long id);



    @Operation(summary="Retrieving instructors with optional filters",
        description="Apply optional filters to retrieve a list of instructors. If no filters are given, it retrieves all instructors"
    )
    @ApiResponse(responseCode="200", description="Retrieved instructors successfully")
    List<InstructorResponse> getInstructors(String firstName, String lastName);  




    @Operation(summary="Create a new instructor",
        description="Add a new instructor if all fields are valid"
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Instructor created successfully"),
        @ApiResponse(responseCode="400", description="Invalid value for any field", content=@Content),
        @ApiResponse(responseCode="409", description="Conflict: Email already exists", content=@Content)  
    })
    InstructorResponse createInstructor(@Valid InstructorCreateRequest instructorRequest);

}
