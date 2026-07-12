package gr.techpro.absence.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.techpro.absence.dto.request.InstructorCreateRequest;
import gr.techpro.absence.dto.response.InstructorResponse;
import gr.techpro.absence.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instructors")
@Tag(name = "Instructor Management", description = "Endpoints for creating and retrieving instructors")
public class InstructorController {

    private InstructorService service;
    
    public InstructorController(InstructorService service){
        this.service = service;
    }


    @GetMapping("/{id}")
    @Operation(summary="Retrieves an instructor",
        description="Retrieves an existing instructor based on their id (surrogate key)"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Instructor retrieved successfully"),
        @ApiResponse(responseCode="404", description="Instructor with given id doesn't exist", content=@Content)
    })
    public InstructorResponse getInstructor(@PathVariable Long id){
        return service.getInstructorById(id);
    }


    @GetMapping
    @Operation(summary="Retrieving instructors with optional filters",
        description="Apply optional filters to retrieve a list of instructors. If no filters are given, it retrieves all instructors"
    )
    @ApiResponse(responseCode="200", description="Retrieved instructors successfully")
    public List<InstructorResponse> getInstructors(
        @RequestParam(required=false) String firstName, 
        @RequestParam(required=false) String lastName
    ){
        return service.getFilteredInstructors(firstName, lastName);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="Create a new instructor",
        description="Add a new instructor if all fields are valid"
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Instructor created successfully"),
        @ApiResponse(responseCode="400", description="Invalid value for any field", content=@Content),
        @ApiResponse(responseCode="409", description="Conflict: Email already exists", content=@Content)  
    })
    public InstructorResponse createInstructor(@Valid @RequestBody InstructorCreateRequest instructorRequest){
        return service.registerInstructor(instructorRequest);
    }
}
