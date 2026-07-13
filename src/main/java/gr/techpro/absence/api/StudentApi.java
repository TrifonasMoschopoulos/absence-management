package gr.techpro.absence.api;


import java.util.List;

import gr.techpro.absence.dto.request.StudentCreateRequest;
import gr.techpro.absence.dto.request.StudentPatchRequest;
import gr.techpro.absence.dto.response.StudentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@Tag(name = "Student Management", description = "Endpoints for creating, retrieving, updating and deleting students")
public interface StudentApi {
    
    
    @Operation(summary = "Retrieves a student based on their id",
        description = "Retrieves a student by their surrogate key. The provided id must belong to a registered student"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Student retrieved successfully"),
        @ApiResponse(responseCode="404", description="Provided id doesn't exist", content=@Content)
    })
    StudentResponse getStudent(Long id);
    
    
    @Operation(summary = "Retrieving students with optional filters", 
        description = "Retrieve a list of students with filtering options. If no filters are provided, it retrieves all students"
    )
    @ApiResponse(responseCode="200", description="Students retrieved successfully")
    List<StudentResponse> getStudents(String firstName, String lastName);



    @Operation(summary = "Register a new student",
        description = """
        Creates a new student and generates a unique student number. 
        The Email must not already be in use.
        """
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Student created successfully"),
        @ApiResponse(responseCode="400", description="Validation error (blank fields or invalid email address)", content=@Content),
        @ApiResponse(responseCode="409", description="Conflict: Email already exists", content=@Content)
    })
    StudentResponse createStudent(@Valid StudentCreateRequest student);



    @Operation(summary = "Partially update an existing student",
        description = """
        Updates the details of an existing student defined by the provided id.
        The provided id must belong to a registered student.
        At least one field must be given.
        If provided with a new email, it must not belong to another student.
        """
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Student updated successfully"),
        @ApiResponse(responseCode="400", description="Validation error (blank fields/invalid email address). At least one field must be given", content=@Content),
        @ApiResponse(responseCode="404", description="Provided id doesn't exist", content=@Content),
        @ApiResponse(responseCode="409", description="Conflict: Email already exists", content=@Content),
    })
    StudentResponse updateStudent(Long id, @Valid StudentPatchRequest request);

    

    @Operation(summary="Delete a student based on their id",
        description="""
        Delete an existing student with the provided id.
        The provided id must belong to a registered student.
        """
    )
    @ApiResponses({
        @ApiResponse(responseCode="204", description="Student deleted successfully"),
        @ApiResponse(responseCode="404", description="Provided id doesn't exist", content=@Content)
    })
    void deleteStudent(Long id);

}
