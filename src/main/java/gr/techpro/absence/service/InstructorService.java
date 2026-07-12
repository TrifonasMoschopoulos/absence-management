package gr.techpro.absence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gr.techpro.absence.dto.request.InstructorCreateRequest;
import gr.techpro.absence.dto.response.InstructorResponse;
import gr.techpro.absence.entity.Instructor;
import gr.techpro.absence.repository.InstructorRepository;
import gr.techpro.absence.exception.ResourceAlreadyExistsException;
import gr.techpro.absence.exception.ResourceNotFoundException;
import gr.techpro.absence.mapper.InstructorMapper;

@Service
public class InstructorService {
    private InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    public InstructorResponse getInstructorById(Long id){
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Instructor with id " + id + " not found")
        );
        return InstructorMapper.toResponse(instructor);
    }
    
    
    public List<InstructorResponse> getFilteredInstructors(String firstName, String lastName){
        List<Instructor> instructors = instructorRepository.searchInstructors(firstName, lastName);
        return InstructorMapper.toResponse(instructors);
    }


    public InstructorResponse registerInstructor(InstructorCreateRequest instructorRequest){
        if (instructorRepository.existsByEmail(instructorRequest.getEmail()))
            throw new ResourceAlreadyExistsException("Email is already in use");

        Instructor instructor = InstructorMapper.toInstructor(instructorRequest);
        instructorRepository.save(instructor);

        return InstructorMapper.toResponse(instructor);
    }


}