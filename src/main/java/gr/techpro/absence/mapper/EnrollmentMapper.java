package gr.techpro.absence.mapper;

import gr.techpro.absence.dto.request.EnrollmentCreateRequest;
import gr.techpro.absence.dto.response.EnrollmentResponse;
import gr.techpro.absence.dto.response.EnrollmentResponse.StudentSummary;
import gr.techpro.absence.dto.response.ModuleSummary;
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
        StudentSummary studentSummary = toStudentSummary(enrollment.getStudent());

        return new EnrollmentResponse(
            enrollment.getId(),
            enrollment.getStatus(),
            enrollment.getEnrolledAt(),
            studentSummary,
            moduleSummary
        );
    }


    private static EnrollmentResponse.StudentSummary toStudentSummary(Student student){
        return new StudentSummary(
            student.getId(),
            student.getStudentNumber(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail()
        );
    }

}
