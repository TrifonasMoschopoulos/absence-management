package gr.techpro.absence.api;

import gr.techpro.absence.dto.request.EnrollmentCreateRequest;
import gr.techpro.absence.dto.response.enrollment.EnrollmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Enrollment Management", description="Endpoints to create, retrieve and cancel enrollments")
public interface EnrollmentApi {
    
    @Operation(summary="Retrieve an enrollment",
        description="Access an existing enrollment by their id (surrogate key)."
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Enrollment retrieved successfully"),
        @ApiResponse(responseCode="404", description="There is no enrollment with given id", content=@Content)
    })
    EnrollmentResponse getEnrollment(Long id);


    @Operation(summary="Create an enrollment",
        description="""
        Create a new enrollment to represent a student enrolled to a module.
        The client must provide student's and module's IDs and they must belong to an existing student and an existing module.
        Optionally, the status of the enrollment can be provided.
        Accepted values for status are: ACTIVE, DROPPED, COMPLETED.
        If no status given, it will be set to ACTIVE. 
        """)
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Enrollment created successfully"),
        @ApiResponse(responseCode="400", description="Invalid value for status OR a mandatory field not provided", content=@Content),
        @ApiResponse(responseCode="404", description="Student/Module id doesn't belong to an existing student/module", content=@Content),
        @ApiResponse(responseCode="422", description="Attempt to register a student in a course for which they are already enrolled", content=@Content)
    })
    EnrollmentResponse createEnrollment(@Valid EnrollmentCreateRequest request);



    @Operation(summary="Cancel an enrollment",
        description="""
        Cancels an existing enrollment of a student to a module.
        It doesn't delete the enrollment row, but it instead it marks the
        enrollment as DROPPED.
        """
    )
    @ApiResponses({
        @ApiResponse(responseCode="204", description="Enrollment cancelled successfully"),
        @ApiResponse(responseCode="404", description="Enrollment with given id doesn't exist", content=@Content),
        @ApiResponse(responseCode="422", description="Can only cancel ACTIVE enrollments", content=@Content)
    })
    void cancelEnrollment(Long id);
}


