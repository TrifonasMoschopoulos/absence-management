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

import gr.techpro.absence.api.InstructorApi;
import gr.techpro.absence.dto.request.InstructorCreateRequest;
import gr.techpro.absence.dto.response.instructor.InstructorResponse;
import gr.techpro.absence.service.InstructorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController implements InstructorApi{

    private InstructorService service;
    
    public InstructorController(InstructorService service){
        this.service = service;
    }


    @GetMapping("/{id}")
    @Override
    public InstructorResponse getInstructor(@PathVariable Long id){
        return service.getInstructorById(id);
    }


    @GetMapping
    @Override
    public List<InstructorResponse> getInstructors(
        @RequestParam(required=false) String firstName, 
        @RequestParam(required=false) String lastName
    ){
        return service.getFilteredInstructors(firstName, lastName);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public InstructorResponse createInstructor(@Valid @RequestBody InstructorCreateRequest instructorRequest){
        return service.registerInstructor(instructorRequest);
    }
}
