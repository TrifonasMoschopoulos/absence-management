package gr.techpro.absence.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import gr.techpro.absence.dto.request.TeachingAssignmentCreateRequest;
import gr.techpro.absence.dto.response.teaching_assignment.InstructorAssignmentResponse;
import gr.techpro.absence.dto.response.teaching_assignment.TeachingAssignmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;




@Tag(name="Teaching Assignment Management", description="Endpoints to assign instructors to modules and access teaching assignments information")
public interface TeachingAssignmentApi {
    
    @Operation(summary = "Assign an instructor to a module",
        description = """
        Create a new teaching assignment to allocate a course to a teacher.
        The provided moduleId and instructorId must belong to existing module and instructor.
        Field 'role' is optional and accepted values are: 'LEAD', 'ASSISTANT'
        """
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Successfully assigned the instructor to the module"),
        @ApiResponse(responseCode="400", description="Invalid JSON OR missing one of 'moduleId'/'instructorId' OR invalid value for 'role'", content=@Content),
        @ApiResponse(responseCode="404", description="The provided moduleId or instructorId not found", content=@Content),
        @ApiResponse(responseCode="409", description="Conflict: Module with 'moduleId' is already assigned to instructor with 'instructorId'", content=@Content)
    })
    TeachingAssignmentResponse createTeachingAssignment(@Valid @RequestBody TeachingAssignmentCreateRequest request);




    @Operation(summary="Retrieve the modules assigned to an instructor",
        description="Retrieves the courses assigned to a teacher based on their ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Module is successfully retrieved"),
        @ApiResponse(responseCode="404", description="The provided 'instructorId' doesn't belong to an existing instructor", content=@Content)
    })
    List<InstructorAssignmentResponse> getInstructorAssignments(@PathVariable Long id);
}
