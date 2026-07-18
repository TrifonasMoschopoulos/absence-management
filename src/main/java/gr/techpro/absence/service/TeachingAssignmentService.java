package gr.techpro.absence.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.techpro.absence.dto.request.TeachingAssignmentCreateRequest;
import gr.techpro.absence.dto.response.InstructorAssignmentResponse;
import gr.techpro.absence.dto.response.TeachingAssignmentResponse;
import gr.techpro.absence.entity.TeachingAssignment;
import gr.techpro.absence.entity.Instructor;
import gr.techpro.absence.entity.Module;
import gr.techpro.absence.exception.ResourceAlreadyExistsException;
import gr.techpro.absence.exception.ResourceNotFoundException;
import gr.techpro.absence.mapper.TeachingAssignmentMapper;
import gr.techpro.absence.repository.InstructorRepository;
import gr.techpro.absence.repository.ModuleRepository;
import gr.techpro.absence.repository.TeachingAssignmentRepository;


@Service
public class TeachingAssignmentService {
    
    private TeachingAssignmentRepository assignmentRepository;
    private ModuleRepository moduleRepository;
    private InstructorRepository instructorRepository;


    public TeachingAssignmentService(TeachingAssignmentRepository assignmentRepository, ModuleRepository moduleRepository, InstructorRepository instructorRepository){
        this.moduleRepository = moduleRepository;
        this.instructorRepository = instructorRepository;
        this.assignmentRepository = assignmentRepository;
    }


    public List<InstructorAssignmentResponse> getInstructorAssignments(Long instructorId){
        if (!instructorRepository.existsById(instructorId))
            throw new ResourceNotFoundException("Instructor with id '" + instructorId +"' not found");

        List<TeachingAssignment> assignments = assignmentRepository.findAllByInstructorIdWithModule(instructorId);
        return TeachingAssignmentMapper.toInstructorAssignmentResponse(assignments);
    }


    @Transactional
    public TeachingAssignmentResponse assignInstructorToModule(TeachingAssignmentCreateRequest request){
        if (!moduleRepository.existsById(request.getModuleId()))
            throw new ResourceNotFoundException("Module with id '" + request.getModuleId() +"' not found");
        if (!instructorRepository.existsById(request.getInstructorId()))
            throw new ResourceNotFoundException("Instructor with id '" + request.getInstructorId() +"' not found");
        if (assignmentRepository.existsByModuleIdAndInstructorId(request.getModuleId(), request.getInstructorId()))
            throw new ResourceAlreadyExistsException(String.format("Instructor with ID '%d' is already assigned to Module with ID '%d'", request.getInstructorId(), request.getModuleId()));

        Module module = moduleRepository.getReferenceById(request.getModuleId());
        Instructor instructor = instructorRepository.getReferenceById(request.getInstructorId());

        TeachingAssignment assignment = TeachingAssignmentMapper.toTeachingAssignment(module, instructor, request.getRole());

        assignment = assignmentRepository.save(assignment);
        return TeachingAssignmentMapper.toTeachingAssignmentResponse(assignment); 
    }
}
