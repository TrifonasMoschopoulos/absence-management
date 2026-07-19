package gr.techpro.absence.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import gr.techpro.absence.dto.request.SessionCreateRequest;
import gr.techpro.absence.dto.response.session.SessionResponse;
import gr.techpro.absence.entity.Session;
import gr.techpro.absence.entity.Module;
import gr.techpro.absence.exception.BusinessRuleViolationException;
import gr.techpro.absence.exception.ResourceNotFoundException;
import gr.techpro.absence.mapper.SessionMapper;
import gr.techpro.absence.repository.ModuleRepository;
import gr.techpro.absence.repository.SessionRepository;
import gr.techpro.absence.repository.specification.SessionSpecifications;


@Service
public class SessionService {
    
    private final SessionRepository sessionRepository;
    private final ModuleRepository moduleRepository;

    public SessionService(SessionRepository sessionRepository, ModuleRepository moduleRepository){
        this.sessionRepository = sessionRepository;
        this.moduleRepository = moduleRepository;
    }


    public List<SessionResponse> getSessionsByModuleId(Long moduleId, LocalDate from, LocalDate to){
        if (from != null && to != null && from.isAfter(to))
            throw new BusinessRuleViolationException("Parameter Date 'from' must not be after Date 'to'");
        if (!moduleRepository.existsById(moduleId))
            throw new ResourceNotFoundException("Module with id '" + moduleId + "' not found");

        Specification<Session> spec = Specification.allOf(
            SessionSpecifications.hasModuleId(moduleId),
            SessionSpecifications.fromDate(from),
            SessionSpecifications.toDate(to)
        );
        
        List<Session> sessions = sessionRepository.findAll(spec, Sort.by("sessionDate", "startTime").descending());
        return SessionMapper.toResponse(sessions);
    }



    public SessionResponse createSessionForModule(Long moduleId, SessionCreateRequest request){
        if (!moduleRepository.existsById(moduleId))
            throw new ResourceNotFoundException("Module with id '" + moduleId + "' not found");
        if (!request.getStartTime().isBefore(request.getEndTime()))
            throw new BusinessRuleViolationException("Session's 'startTime' must precede 'endTime'");

        Module module = moduleRepository.getReferenceById(moduleId);
        Session session = SessionMapper.toSession(request, module);

        sessionRepository.save(session);
        return SessionMapper.toResponse(session);
    }
}
