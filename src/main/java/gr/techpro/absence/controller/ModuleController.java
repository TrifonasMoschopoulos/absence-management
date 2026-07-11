package gr.techpro.absence.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import gr.techpro.absence.dto.request.ModuleCreateRequest;
import gr.techpro.absence.dto.request.ModulePatchRequest;
import gr.techpro.absence.dto.response.ModuleResponse;
import gr.techpro.absence.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/modules")
@Validated
@Tag(name="Module Management", description="Endpoints for creating, retrieving, updating and deleting modules")
public class ModuleController {
    private final ModuleService moduleService;
    
    public ModuleController(ModuleService moduleService){
        this.moduleService = moduleService;
    }

    @GetMapping("/{id}")
    @Operation(summary="Retrieving a module based on their id",
        description="Retrieve a module by their surrogate key. The provided key must belong to an existing module"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Module retrieved successfully"),
        @ApiResponse(responseCode="404", description="Provided id doesn't exist", content=@Content)
    })
    public ModuleResponse getModule(@PathVariable Long id){
        return moduleService.getModuleById(id);
    }


    @GetMapping
    @Operation(summary="Retrieving modules with optional filters",
        description="Retrieve a list of modules with filtering options. If no filters are provided, it retrieves all modules"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Modules retrieved successfully"),
        @ApiResponse(responseCode="400", description="Invalid value for any provided field", content=@Content)
    })
    public List<ModuleResponse> getModules(
            @RequestParam(required=false) String title, 
            @RequestParam(required=false) @Positive(message="Credits must be a positive number (>0)") Integer credits, 
            @RequestParam(required=false) @Pattern(regexp="FALL|SPRING|SUMMER", message="Invalid semester. Accepted values: FALL, SPRING, SUMMER") String semester, 
            @RequestParam(required=false) @Min(value=1000, message="Academic Year must be 4-digit number") @Max(value=9999, message="Academic Year must be 4-digit number") Integer academicYear
    ){
        return moduleService.getFilteredModules(title, credits, semester, academicYear);
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="Create a new module",
        description="Add a new module and generate a unique module code"
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Module created successfully"),
        @ApiResponse(responseCode="400", description="Invalid value for any field", content=@Content)
    })
    public ModuleResponse createModule(@Valid @RequestBody ModuleCreateRequest module){
        return moduleService.registerModule(module);
    }


    @PatchMapping("/{id}")
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
    public ModuleResponse updateModule(@PathVariable Long id, @Valid @RequestBody ModulePatchRequest request){
        return moduleService.patchModule(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary="Delete a module",
        description="Delete a module by its id (surrogate key). Module with the given id must exist"
    )
    @ApiResponses({
        @ApiResponse(responseCode="204", description="Module deleted successfully"),
        @ApiResponse(responseCode="404", description="Module with the given id doesn't exist", content=@Content)
    })
    public void deleteModule(@PathVariable Long id){
        moduleService.deleteModuleById(id);
    }

}
