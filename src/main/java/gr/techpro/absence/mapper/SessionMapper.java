package gr.techpro.absence.mapper;

import java.util.List;

import gr.techpro.absence.dto.request.SessionCreateRequest;
import gr.techpro.absence.dto.response.session.SessionResponse;
import gr.techpro.absence.dto.response.shared.SessionSummary;
import gr.techpro.absence.entity.Session;
import gr.techpro.absence.entity.Module;

public class SessionMapper {

    private SessionMapper() {}

    public static Session toSession(SessionCreateRequest request, Module module){
        return new Session(
            module, 
            request.getSessionDate(), 
            request.getStartTime(), 
            request.getEndTime(), 
            request.getType(),
            request.getTopic()  
        );
    }


    public static SessionResponse toResponse(Session session){
        return new SessionResponse(
            session.getId(),
            session.getSessionDate(),
            session.getStartTime(),
            session.getEndTime(),
            session.getType(),
            session.getTopic()
        );
    }


    public static List<SessionResponse> toResponse(List<Session> sessions){
        return sessions.stream()
                       .map(SessionMapper::toResponse)
                       .toList();
    }


    public static SessionSummary toSummary(Session session){
        return new SessionSummary(
            session.getId(),
            session.getSessionDate(),
            session.getModule().getId(),
            session.getModule().getCode(),
            session.getModule().getTitle()
        );
    }
    
}
