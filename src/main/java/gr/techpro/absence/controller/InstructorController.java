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
public class InstructorController {

    private InstructorService service;
    
    public InstructorController(InstructorService service){
        this.service = service;
    }


    @GetMapping("/{id}")
    public InstructorResponse getInstructor(@PathVariable Long id){
        return service.getInstructorById(id);
    }


    @GetMapping
    public List<InstructorResponse> getInstructors(
        @RequestParam(required=false) String firstName, 
        @RequestParam(required=false) String lastName
    ){
        return service.getFilteredInstructors(firstName, lastName);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstructorResponse createInstructor(@Valid @RequestBody InstructorCreateRequest instructorRequest){
        return service.registerInstructor(instructorRequest);
    }
}
