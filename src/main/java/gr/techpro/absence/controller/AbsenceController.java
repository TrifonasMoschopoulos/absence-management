package gr.techpro.absence.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.techpro.absence.api.AbsenceApi;
import gr.techpro.absence.dto.request.CreateAbsenceRequest;
import gr.techpro.absence.dto.request.UpdateJustificationRequest;
import gr.techpro.absence.dto.response.absence.AbsenceDetailsResponse;
import gr.techpro.absence.dto.response.absence.AbsenceSummaryResponse;
import gr.techpro.absence.dto.response.absence.CreateAbsenceResponse;
import gr.techpro.absence.dto.response.absence.UpdateJustificationResponse;
import gr.techpro.absence.service.AbsenceService;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController implements AbsenceApi{

    private final AbsenceService service;

    public AbsenceController(AbsenceService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    @Override
    public AbsenceDetailsResponse getAbsence(@PathVariable Long id) {
        return service.getAbsenceById(id);
    }

    @GetMapping
    @Override
    public List<AbsenceSummaryResponse> filterAbsences(
        @RequestParam(required=false) Long studentId, 
        @RequestParam(required=false) Long moduleId,
        @RequestParam(required=false) Long sessionId
    ) {
            return service.findAbsences(studentId, moduleId, sessionId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public CreateAbsenceResponse createAbsence(@RequestBody CreateAbsenceRequest request) {
        return service.recordAbsence(request);
    }

    @PatchMapping("/{id}/justification")
    @Override
    public UpdateJustificationResponse updateAbsenceJustification(
        @PathVariable Long id,  
        @RequestBody UpdateJustificationRequest request
    ) {
        return service.updateJustification(id, request);
    }
    

}
