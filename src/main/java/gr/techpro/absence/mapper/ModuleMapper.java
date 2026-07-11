package gr.techpro.absence.mapper;

import gr.techpro.absence.entity.Module;

import java.util.List;
import java.util.stream.Collectors;

import gr.techpro.absence.dto.request.ModuleCreateRequest;
import gr.techpro.absence.dto.response.ModuleResponse;
import gr.techpro.absence.enums.Semester;

public class ModuleMapper {
    
    public static ModuleResponse toResponse(Module module){
        return new ModuleResponse(
            module.getId(),
            module.getCode(),
            module.getTitle(),
            module.getCredits(),
            module.getSemester(),
            module.getAcademicYear()
        );
    }

    public static List<ModuleResponse> toResponseList(List<Module> modules){
        return modules.stream()
                      .map(ModuleMapper::toResponse)
                      .collect(Collectors.toList());
    }


    public static Module toModule(ModuleCreateRequest request, String moduleCode){
        // Convert semester to Enum type
        Semester semester = Semester.valueOf(request.getSemester());
        
        return new Module(
            moduleCode,
            request.getTitle(),
            request.getCredits(),
            semester,
            request.getAcademicYear()
        );
    }
}
