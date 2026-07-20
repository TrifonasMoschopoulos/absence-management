package gr.techpro.absence.mapper;

import java.util.List;

import gr.techpro.absence.dto.response.absence.AbsenceDetailsResponse;
import gr.techpro.absence.dto.response.absence.AbsenceSummaryResponse;
import gr.techpro.absence.dto.response.absence.CreateAbsenceResponse;
import gr.techpro.absence.dto.response.absence.UpdateJustificationResponse;
import gr.techpro.absence.dto.response.shared.SessionSummary;
import gr.techpro.absence.dto.response.shared.StudentIdentification;
import gr.techpro.absence.dto.response.shared.StudentSummary;
import gr.techpro.absence.entity.Absence;

public class AbsenceMapper {
    
    private AbsenceMapper() {}

    public static AbsenceDetailsResponse toDetailsResponse(Absence absence){
        StudentSummary studentSummary = StudentMapper.toSummary(absence.getEnrollment().getStudent());
        SessionSummary sessionSummary = SessionMapper.toSummary(absence.getSession());
        
        return new AbsenceDetailsResponse(
            absence.getId(), 
            absence.getStatus(), 
            absence.isJustified(), 
            absence.getJustification(), 
            studentSummary, 
            sessionSummary, 
            absence.getRecordedAt(), 
            absence.getUpdatedAt()
        );
    }


    public static AbsenceSummaryResponse toSummaryResponse(Absence absence){
        StudentIdentification studentIdentification = StudentMapper.toIdentification(absence.getEnrollment().getStudent());
        SessionSummary sessionSummary = SessionMapper.toSummary(absence.getSession());

        return new AbsenceSummaryResponse(
            absence.getId(), 
            absence.getStatus(), 
            absence.isJustified(), 
            studentIdentification, 
            sessionSummary
        );
    }


    public static List<AbsenceSummaryResponse> toSummaryResponse(List<Absence> absences){
        return absences.stream()
                       .map(AbsenceMapper::toSummaryResponse)
                       .toList();
    }


    public static CreateAbsenceResponse toCreateResponse(Absence absence){
        return new CreateAbsenceResponse(
            absence.getId(), 
            absence.getEnrollment().getId(), 
            absence.getSession().getId(), 
            absence.getStatus(), 
            absence.isJustified(),
            absence.getJustification()
        );
    }


    public static UpdateJustificationResponse toUpdateJustificationResponse(Absence absence){
        return new UpdateJustificationResponse(
            absence.getId(),
            absence.getEnrollment().getId(), 
            absence.getSession().getId(), 
            absence.getStatus(),
            absence.isJustified(),
            absence.getJustification()
        );
    }
}
