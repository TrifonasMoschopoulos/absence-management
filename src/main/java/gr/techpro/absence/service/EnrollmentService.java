package gr.techpro.absence.service;

import org.springframework.stereotype.Service;

import gr.techpro.absence.dto.request.EnrollmentCreateRequest;
import gr.techpro.absence.dto.response.EnrollmentResponse;
import gr.techpro.absence.entity.Enrollment;
import gr.techpro.absence.entity.Student;
import gr.techpro.absence.enums.EnrollmentStatus;
import gr.techpro.absence.entity.Module;
import gr.techpro.absence.exception.BusinessRuleViolationException;
import gr.techpro.absence.exception.ResourceNotFoundException;
import gr.techpro.absence.mapper.EnrollmentMapper;
import gr.techpro.absence.repository.EnrollmentRepository;
import gr.techpro.absence.repository.ModuleRepository;
import gr.techpro.absence.repository.StudentRepository;
import jakarta.transaction.Transactional;

@Service
public class EnrollmentService {
    
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;

    public EnrollmentService(
        EnrollmentRepository enrollmentRepository, 
        StudentRepository studentRepository, 
        ModuleRepository moduleRepository){
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;
    }


    public EnrollmentResponse getEnrollmentById(Long id){
        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Enrollment with id " + id + " not found")
        );
        return EnrollmentMapper.toResponse(enrollment);
    }


    @Transactional
    public EnrollmentResponse enrollStudentToModule(EnrollmentCreateRequest request){
        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(
            () -> new ResourceNotFoundException("Student with id " + request.getStudentId() + " not found")
        );

        Module module = moduleRepository.findById(request.getModuleId()).orElseThrow(
            () -> new ResourceNotFoundException("Module with id " + request.getModuleId() + " not found")
        );
        
        if (enrollmentRepository.existsByStudentIdAndModuleId(request.getStudentId(), request.getModuleId()))
            throw new BusinessRuleViolationException("Student with studentId: '" + request.getStudentId() + "' is already enrolled to module with moduleId: '" + request.getModuleId() +"'");

        Enrollment enrollment = EnrollmentMapper.toEnrollment(request, student, module);
        enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(enrollment);
    }


    @Transactional
    public void cancelEnrollment(Long id){
        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Enrollment with id: '" + id + "' doesn't exist")
        );

        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE)
            throw new BusinessRuleViolationException("Can only cancel active enrollments. Current status: " + enrollment.getStatus());

        enrollment.setStatus(EnrollmentStatus.DROPPED);
    }
}
