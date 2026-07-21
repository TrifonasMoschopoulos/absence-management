package gr.techpro.absence.mapper;

import gr.techpro.absence.dto.response.report.StudentAtRiskResponse;
import gr.techpro.absence.dto.response.shared.StudentSummary;
import gr.techpro.absence.entity.Student;

public class ReportMapper {
    
    private ReportMapper() {}

    public static StudentAtRiskResponse toStudentAtRiskResponse(Student student, long totalSessions, long absent, double absenceRate){
        StudentSummary studentSummary = StudentMapper.toSummary(student);
        return new StudentAtRiskResponse(studentSummary, totalSessions, absent, absenceRate);
    }

}
