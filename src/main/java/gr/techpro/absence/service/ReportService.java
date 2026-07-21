package gr.techpro.absence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gr.techpro.absence.dto.response.report.StudentAtRiskResponse;
import gr.techpro.absence.dto.response.report.StudentModuleSummaryResponse;
import gr.techpro.absence.entity.Absence;
import gr.techpro.absence.entity.Enrollment;
import gr.techpro.absence.enums.AttendanceStatus;
import gr.techpro.absence.exception.ResourceNotFoundException;
import gr.techpro.absence.mapper.ReportMapper;
import gr.techpro.absence.repository.AbsenceRepository;
import gr.techpro.absence.repository.EnrollmentRepository;
import gr.techpro.absence.repository.ModuleRepository;

@Service
public class ReportService {
    
    private final double atRiskThreshold;
    
    private final EnrollmentRepository enrollmentRepository;
    private final AbsenceRepository absenceRepository;
    private final ModuleRepository moduleRepository;


    public ReportService(
        @Value("${absence.threshold}") Double atRiskThreshold,
        EnrollmentRepository enrollmentRepository,
        AbsenceRepository absenceRepository, ModuleRepository moduleRepository
    ){
        this.atRiskThreshold = atRiskThreshold;
        this.enrollmentRepository = enrollmentRepository;
        this.absenceRepository = absenceRepository;
        this.moduleRepository = moduleRepository;
    }



    public StudentModuleSummaryResponse getStudentModuleSummary(Long studentId, Long moduleId){
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndModuleId(studentId, moduleId).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Enrollment with student ID '%d' and module ID '%d' not found", studentId, moduleId))
        );
         List<Absence> absences = absenceRepository.findByEnrollmentId(enrollment.getId());
        
        int totalSessions = absences.size();
        long attended = 0, absent = 0, justified = 0;
        
        for (Absence absence : absences){
            if (absence.getStatus() == AttendanceStatus.ABSENT){ 
                absent ++;
                if (absence.isJustified()) justified ++;
            } else { 
                attended ++;
            }
        }
        double absenceRatePercent = calculateAbsenceRate(absent, totalSessions);
        return new StudentModuleSummaryResponse(totalSessions, attended, absent, justified, absenceRatePercent);
    }



    public List<StudentAtRiskResponse> getAtRiskStudentsForModule(Long moduleId, Double atRiskThreshold){
        Double finalAtRiskThreshold = (atRiskThreshold != null) ? atRiskThreshold : this.atRiskThreshold;

        if (!moduleRepository.existsById(moduleId))
            throw new ResourceNotFoundException("Module with the provided id (=" + moduleId +") not found");

        List<Absence> allAbsences = absenceRepository.findAllAbsencesWithStudentByModuleId(moduleId);

        Map<Long, List<Absence>> groupedAbsencesByEnrollment = allAbsences.stream().collect(Collectors.groupingBy(
                                                               (absence) -> absence.getEnrollment().getId()));
        List<StudentAtRiskResponse> studentsAtRisk = new ArrayList<>();

        for (List<Absence> absences : groupedAbsencesByEnrollment.values()){
            long totalSessions = absences.size();
            long absent = countAbsentStatus(absences);
            double absenceRatePercent = calculateAbsenceRate(absent, totalSessions);
            if (absenceRatePercent > finalAtRiskThreshold){
                studentsAtRisk.add(
                    ReportMapper.toStudentAtRiskResponse(
                        absences.getFirst().getEnrollment().getStudent(),
                        totalSessions, 
                        absent, 
                        absenceRatePercent));
            } 
        }
        return studentsAtRisk;
    }




    private long countAbsentStatus(List<Absence> absences){
        return absences.stream().filter((e) -> e.getStatus() == AttendanceStatus.ABSENT).count();
    }

    

    private double calculateAbsenceRate(long absent, long totalSessions){
        return (totalSessions == 0) ? 0 : ((float) absent / totalSessions) * 100.0;
    }



}
