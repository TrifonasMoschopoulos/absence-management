package gr.techpro.absence.mapper;

import gr.techpro.absence.dto.request.EnrollmentCreateRequest;
import gr.techpro.absence.dto.response.enrollment.EnrollmentResponse;
import gr.techpro.absence.dto.response.shared.ModuleSummary;
import gr.techpro.absence.dto.response.shared.StudentSummary;
import gr.techpro.absence.entity.Enrollment;
import gr.techpro.absence.entity.Student;
import gr.techpro.absence.entity.Module;


public class EnrollmentMapper {

    public static Enrollment toEnrollment(EnrollmentCreateRequest request, Student student, Module module){
        return new Enrollment(
            student, module, request.getStatus()
        );
    }
    
    public static EnrollmentResponse toResponse(Enrollment enrollment){
        ModuleSummary moduleSummary = ModuleMapper.toSummary(enrollment.getModule());
        StudentSummary studentSummary = StudentMapper.toSummary(enrollment.getStudent());

        return new EnrollmentResponse(
            enrollment.getId(),
            enrollment.getStatus(),
            enrollment.getEnrolledAt(),
            studentSummary,
            moduleSummary
        );
    }

}
