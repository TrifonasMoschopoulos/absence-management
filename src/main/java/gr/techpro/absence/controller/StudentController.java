package gr.techpro.absence.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.techpro.absence.dto.request.StudentCreateRequest;
import gr.techpro.absence.dto.request.StudentPatchRequest;
import gr.techpro.absence.dto.response.StudentResponse;
import gr.techpro.absence.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "Endpoints for creating, retrieving, updating and deleting students")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    
    @GetMapping
    @Operation(summary = "Retrieving students with optional filters", 
        description = "Retrieve a list of students with filtering options. If no filters are provided, it retrieves all students"
    )
    @ApiResponse(responseCode="200", description="Students retrieved successfully")
    public List<StudentResponse> getStudents(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return studentService.getFilteredStudents(firstName, lastName);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a student based on their id",
        description = "Retrieve a student by their surrogate key. The provided id must belong to a registered student"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Student retrieved successfully"),
        @ApiResponse(responseCode="404", description="Provided id doesn't exist", content=@Content)
    })
    public StudentResponse getStudent(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public StudentResponse createStudent(@Valid @RequestBody StudentCreateRequest student){
        return studentService.registerStudent(student);
    }

    @PatchMapping("/{id}")
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
    public StudentResponse updateStudent(@PathVariable Long id, @Valid @RequestBody StudentPatchRequest request){
        return studentService.patchStudent(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }
}
