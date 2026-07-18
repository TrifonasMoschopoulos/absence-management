package gr.techpro.absence.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.techpro.absence.api.TeachingAssignmentApi;
import gr.techpro.absence.dto.request.TeachingAssignmentCreateRequest;
import gr.techpro.absence.dto.response.InstructorAssignmentResponse;
import gr.techpro.absence.dto.response.TeachingAssignmentResponse;
import gr.techpro.absence.service.TeachingAssignmentService;


@RestController
@RequestMapping("/api")
public class TeachingAssignmentController implements TeachingAssignmentApi{
    
    private final TeachingAssignmentService service;

    public TeachingAssignmentController(TeachingAssignmentService service){
        this.service = service;
    }
    

    @Override
    @GetMapping("/instructors/{id}/teaching-assignments")
    public List<InstructorAssignmentResponse> getInstructorAssignments(Long id) {
        return service.getInstructorAssignments(id);
    }


    @Override
    @PostMapping("/teaching-assignments")
    @ResponseStatus(HttpStatus.CREATED)
    public TeachingAssignmentResponse createTeachingAssignment(TeachingAssignmentCreateRequest request) {
        return service.assignInstructorToModule(request);
    }
}
