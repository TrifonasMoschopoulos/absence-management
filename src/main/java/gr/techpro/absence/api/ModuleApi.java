package gr.techpro.absence.api;


import java.util.List;

import org.springframework.validation.annotation.Validated;

import gr.techpro.absence.dto.request.ModuleCreateRequest;
import gr.techpro.absence.dto.request.ModulePatchRequest;
import gr.techpro.absence.dto.response.ModuleResponse;
import gr.techpro.absence.enums.Semester;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;



@Tag(name="Module Management", description="Endpoints to create, retrieve, update and delete modules")
@Validated
public interface ModuleApi {


    @Operation(summary="Retrieve a module",
        description="Retrieves a module by their surrogate key. The provided key must belong to an existing module"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Module retrieved successfully"),
        @ApiResponse(responseCode="404", description="Provided id doesn't exist", content=@Content)
    })
    ModuleResponse getModule(Long id);




    @Operation(summary="Retrieve modules with optional filters",
        description="Retrieve a list of modules with filtering options. If no filters are provided, it retrieves all modules"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Modules retrieved successfully"),
        @ApiResponse(responseCode="400", description="Invalid value for any provided field", content=@Content)
    })
    List<ModuleResponse> getModules(
        String title,

        @Positive(message="Credits must be a positive number (>0)") 
        Integer credits,

        Semester semester,

        @Min(value=1000, message="Academic Year must be 4-digit number") 
        @Max(value=9999, message="Academic Year must be 4-digit number") 
        Integer academicYear
    );
    



    @Operation(summary="Create a new module",
        description="Initiate a new module. All fields are mandatory"
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Module created successfully"),
        @ApiResponse(responseCode="400", description="Invalid value for any field", content=@Content),
        @ApiResponse(responseCode="409", description="Module 'code' already exists", content=@Content)
    })
    ModuleResponse createModule(@Valid ModuleCreateRequest module);



    @Operation(summary="Partially update a module",
        description="""
        Updates the fields given for the module with the provided id (surrogate key). 
        Module with the given id must exist.
        All fields are optional but at least one must be given
        """     
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Module updated successfully"),
        @ApiResponse(responseCode="400", description="Invalid value for any field OR no field provided for update", content=@Content),
        @ApiResponse(responseCode="404", description="Module with the given id doesn't exist", content=@Content)
    })
    ModuleResponse updateModule(Long id, @Valid ModulePatchRequest request);



    @Operation(summary="Delete a module",
        description="Delete a module by its id (surrogate key). Module with the given id must exist"
    )
    @ApiResponses({
        @ApiResponse(responseCode="204", description="Module deleted successfully"),
        @ApiResponse(responseCode="404", description="Module with the given id doesn't exist", content=@Content)
    })
    void deleteModule(Long id);
}
