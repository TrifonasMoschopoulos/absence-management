package gr.techpro.absence.service;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import gr.techpro.absence.dto.request.CreateAbsenceRequest;
import gr.techpro.absence.dto.request.UpdateJustificationRequest;
import gr.techpro.absence.dto.response.absence.AbsenceDetailsResponse;
import gr.techpro.absence.dto.response.absence.AbsenceSummaryResponse;
import gr.techpro.absence.dto.response.absence.CreateAbsenceResponse;
import gr.techpro.absence.dto.response.absence.UpdateJustificationResponse;
import gr.techpro.absence.entity.Absence;
import gr.techpro.absence.entity.Enrollment;
import gr.techpro.absence.entity.Session;
import gr.techpro.absence.enums.AttendanceStatus;
import gr.techpro.absence.enums.EnrollmentStatus;
import gr.techpro.absence.exception.BadRequestException;
import gr.techpro.absence.exception.BusinessRuleViolationException;
import gr.techpro.absence.exception.ResourceAlreadyExistsException;
import gr.techpro.absence.exception.ResourceNotFoundException;
import gr.techpro.absence.mapper.AbsenceMapper;
import gr.techpro.absence.repository.AbsenceRepository;
import gr.techpro.absence.repository.EnrollmentRepository;
import gr.techpro.absence.repository.ModuleRepository;
import gr.techpro.absence.repository.SessionRepository;
import gr.techpro.absence.repository.StudentRepository;
import gr.techpro.absence.repository.specification.AbsenceSpecifications;
import jakarta.transaction.Transactional;


@Service
public class AbsenceService {
    
    private final AbsenceRepository absenceRepository;
    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;
    private final SessionRepository sessionRepository;
    private final EnrollmentRepository enrollmentRepository;

    public AbsenceService(
        AbsenceRepository absenceRepository,
        StudentRepository studentRepository,
        ModuleRepository moduleRepository,
        SessionRepository sessionRepository,
        EnrollmentRepository enrollmentRepository
    ){
        this.absenceRepository = absenceRepository;
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }


    public AbsenceDetailsResponse getAbsenceById(Long id){
        Absence absence = getAbsenceWithDetailsOrThrow(id); 
        return AbsenceMapper.toDetailsResponse(absence);
    }


    public List<AbsenceSummaryResponse> findAbsences(Long studentId, Long moduleId, Long sessionId){
        validateSearchFilters(studentId, moduleId, sessionId);

        Specification<Absence> spec = Specification.allOf(
            AbsenceSpecifications.hasStudentId(studentId),
            AbsenceSpecifications.hasModuleId(moduleId),
            AbsenceSpecifications.hasSessionId(sessionId)
        );

        List<Absence> absences = absenceRepository.findAll(spec, Sort.by("recordedAt").descending());
        return AbsenceMapper.toSummaryResponse(absences);
    }


    @Transactional
    public CreateAbsenceResponse recordAbsence(CreateAbsenceRequest request){
        
        AttendanceStatus status = Objects.requireNonNullElse(request.status(), AttendanceStatus.ABSENT);
        boolean justified = Boolean.TRUE.equals(request.justified());

        Enrollment enrollment = getEnrollmentOrThrow(request.enrollmentId());
        Session session = getSessionOrThrow(request.sessionId());

        validateNoDuplicateAbsence(request.sessionId(), request.enrollmentId());
        validateActiveEnrollment(enrollment.getStatus());
        validateEnrollmentAndSessionMatches(enrollment, session);

        Absence absence = new Absence.Builder(enrollment, session, status)
                                     .justified(justified)
                                     .justification(request.justification())
                                     .build();
        absence = absenceRepository.save(absence);

        return AbsenceMapper.toCreateResponse(absence);
    }


    @Transactional
    public UpdateJustificationResponse updateJustification(Long absenceId, UpdateJustificationRequest request){
        Absence absence = getAbsenceOrThrow(absenceId);
        absence.updateJustification(request.justified(), request.justification());

        return AbsenceMapper.toUpdateJustificationResponse(absence);
    }


    private Absence getAbsenceOrThrow(Long id){
        return absenceRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Absence with the provided id (='" + id + "') not found")
        );
    }


    private Absence getAbsenceWithDetailsOrThrow(Long id){
        return absenceRepository.findByIdWithDetails(id).orElseThrow(
            () -> new ResourceNotFoundException("Absence with the provided id (='" + id + "') not found")
        );
    }
    
    private Enrollment getEnrollmentOrThrow(Long enrollmentId){
        return enrollmentRepository.findById(enrollmentId).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Enrollment with ID '%d' not found", enrollmentId))
        );
    }

    private Session getSessionOrThrow(Long sessionId){
        return sessionRepository.findById(sessionId).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Session with ID '%d' not found", sessionId))
        );
    }


    private void validateSearchFilters(Long studentId, Long moduleId, Long sessionId){
        if (studentId == null && moduleId == null && sessionId == null)
            throw new BadRequestException("At least one filter must be provided");
        
        if (studentId != null && !studentRepository.existsById(studentId))
            throw new ResourceNotFoundException(String.format("Student with ID '%d' not found", studentId));
        if (moduleId != null && !moduleRepository.existsById(moduleId))
            throw new ResourceNotFoundException(String.format("Module with ID '%d' not found", moduleId));
        if (sessionId != null && !sessionRepository.existsById(sessionId))
            throw new ResourceNotFoundException(String.format("Session with ID '%d' not found", sessionId));
    }


    private void validateNoDuplicateAbsence(Long sessionId, Long enrollmentId){
        if (absenceRepository.existsBySessionIdAndEnrollmentId(sessionId, enrollmentId)){
            throw new ResourceAlreadyExistsException(
                String.format("An Absence with the provided session ID (=%d) and enrollment ID (=%d) already exists", sessionId, enrollmentId)
            );
        }
    }

    private void validateActiveEnrollment(EnrollmentStatus status){
        if (status != EnrollmentStatus.ACTIVE) 
            throw new BusinessRuleViolationException("Cannot record an absence/attendance for an inactive enrollment");
    }


    private void validateEnrollmentAndSessionMatches(Enrollment enrollment, Session session){
        if (!enrollment.getModule().getId().equals(session.getModule().getId())) 
            throw new BusinessRuleViolationException("The provided enrollment doesn't correspond to the provided session");
    }
   
}
