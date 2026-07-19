package gr.techpro.absence.mapper;

import java.util.List;

import gr.techpro.absence.dto.response.shared.ModuleSummary;
import gr.techpro.absence.dto.response.teaching_assignment.InstructorAssignmentResponse;
import gr.techpro.absence.dto.response.teaching_assignment.TeachingAssignmentResponse;
import gr.techpro.absence.entity.Instructor;
import gr.techpro.absence.entity.Module;
import gr.techpro.absence.entity.TeachingAssignment;
import gr.techpro.absence.enums.TeachingRole;

public class TeachingAssignmentMapper {
    
    private TeachingAssignmentMapper() {}


    public static TeachingAssignment toTeachingAssignment(Module module, Instructor instructor, TeachingRole role){
        if (role == null)
            return new TeachingAssignment(module, instructor);
        return new TeachingAssignment(module, instructor, role);
    }


    public static InstructorAssignmentResponse toInstructorAssignmentResponse(TeachingAssignment assignment){
        ModuleSummary moduleSummary  = ModuleMapper.toSummary(assignment.getModule());
        return new InstructorAssignmentResponse(assignment.getId(), assignment.getRole(), moduleSummary);
    }


    public static List<InstructorAssignmentResponse> toInstructorAssignmentResponse(List<TeachingAssignment> assignments){
        return assignments.stream()
                          .map(TeachingAssignmentMapper::toInstructorAssignmentResponse)
                          .toList();
    }


    public static TeachingAssignmentResponse toTeachingAssignmentResponse(TeachingAssignment assignment){
        return new TeachingAssignmentResponse(
            assignment.getId(), 
            assignment.getModule().getId(), 
            assignment.getInstructor().getId(), 
            assignment.getRole());
    }

}
