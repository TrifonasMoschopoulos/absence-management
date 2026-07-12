package gr.techpro.absence.mapper;

import java.util.List;

import gr.techpro.absence.dto.request.InstructorCreateRequest;
import gr.techpro.absence.dto.response.InstructorResponse;
import gr.techpro.absence.entity.Instructor;

public class InstructorMapper {
    
    public static InstructorResponse toResponse(Instructor instructor){
        return new InstructorResponse(
            instructor.getId(),
            instructor.getFirstName(),
            instructor.getLastName(),
            instructor.getEmail()
        );
    }

    public static List<InstructorResponse> toResponse(List<Instructor> instructors){
        return instructors.stream()
                          .map(InstructorMapper::toResponse)
                          .toList();
    }

    public static Instructor toInstructor(InstructorCreateRequest request){
        return new Instructor(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail()
        );
    }
}
