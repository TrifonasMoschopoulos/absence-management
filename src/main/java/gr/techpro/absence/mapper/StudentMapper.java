package gr.techpro.absence.mapper;

import java.util.List;
import java.util.stream.Collectors;

import gr.techpro.absence.dto.request.StudentCreateRequest;
import gr.techpro.absence.dto.response.StudentResponse;
import gr.techpro.absence.entity.Student;

public class StudentMapper {
    
    public static Student toStudent(StudentCreateRequest request, String studentNumber){
        return new Student(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            studentNumber
        );
    } 

    public static StudentResponse toResponse(Student student){
        return new StudentResponse(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getStudentNumber(),
            student.getEnrollmentDate()
        );
    }

    public static List<StudentResponse> toResponseList(List<Student> students){
        return students.stream()
                        .map(StudentMapper::toResponse)
                        .collect(Collectors.toList());
    }
}
