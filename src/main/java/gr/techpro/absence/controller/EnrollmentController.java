package gr.techpro.absence.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.techpro.absence.api.EnrollmentApi;
import gr.techpro.absence.dto.request.EnrollmentCreateRequest;
import gr.techpro.absence.dto.response.enrollment.EnrollmentResponse;
import gr.techpro.absence.service.EnrollmentService;


@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController implements EnrollmentApi{
    
    private EnrollmentService service;

    public EnrollmentController(EnrollmentService service){
        this.service = service;
    }


    @GetMapping("/{id}")
    @Override
    public EnrollmentResponse getEnrollment(@PathVariable Long id){
        return service.getEnrollmentById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public EnrollmentResponse createEnrollment(@RequestBody EnrollmentCreateRequest request){
        return service.enrollStudentToModule(request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void cancelEnrollment(@PathVariable Long id){
        service.cancelEnrollment(id);
    }
}
