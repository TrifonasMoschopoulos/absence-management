package gr.techpro.absence.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.techpro.absence.dto.response.report.StudentAtRiskResponse;
import gr.techpro.absence.dto.response.report.StudentModuleSummaryResponse;
import gr.techpro.absence.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    private final ReportService service;

    public ReportController(ReportService service){
        this.service = service;
    }

    @GetMapping("/students/{studentId}/modules{moduleId}")
    public StudentModuleSummaryResponse getStudentModuleSummary(@PathVariable Long studentId, @PathVariable Long moduleId){
        return service.getStudentModuleSummary(studentId, moduleId);
    }


    @GetMapping("/modules/{id}/at-risk")
    public List<StudentAtRiskResponse> getAtRiskStudentsForModule(@PathVariable Long id, @RequestParam(required=false) Double absenceThreshold){
        return service.getAtRiskStudentsForModule(id, absenceThreshold);
    }

}
