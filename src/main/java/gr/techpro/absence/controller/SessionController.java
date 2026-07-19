package gr.techpro.absence.controller;

import java.time.LocalDate;
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

import gr.techpro.absence.api.SessionApi;
import gr.techpro.absence.dto.request.SessionCreateRequest;
import gr.techpro.absence.dto.response.session.SessionResponse;
import gr.techpro.absence.service.SessionService;


@RestController
@RequestMapping("/api/modules/{moduleId}")
public class SessionController implements SessionApi{
    
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @GetMapping("/sessions")
    @Override
    public List<SessionResponse> getSessions(@PathVariable Long moduleId, @RequestParam(required=false) LocalDate from, @RequestParam(required=false) LocalDate to){
        return sessionService.getSessionsByModuleId(moduleId, from, to);
    }


    @PostMapping("/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public SessionResponse createSession(@PathVariable Long moduleId, @RequestBody SessionCreateRequest request){
        return sessionService.createSessionForModule(moduleId, request);
    }

}
