package gr.techpro.absence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gr.techpro.absence.entity.Module;
import gr.techpro.absence.enums.Semester;
import gr.techpro.absence.dto.request.ModuleCreateRequest;
import gr.techpro.absence.dto.request.ModulePatchRequest;
import gr.techpro.absence.dto.response.ModuleResponse;
import gr.techpro.absence.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import gr.techpro.absence.exception.BadRequestException;
import gr.techpro.absence.exception.ResourceAlreadyExistsException;
import gr.techpro.absence.exception.ResourceNotFoundException;
import gr.techpro.absence.mapper.ModuleMapper;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository){
        this.moduleRepository = moduleRepository;
    }

    public ModuleResponse getModuleById(Long id) {
        Module module = moduleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Module with id " + id + " not found.")
        );

        return ModuleMapper.toResponse(module);
    }


    public List<ModuleResponse> getFilteredModules(String title, Integer credits, String semester, Integer academicYear){
        Semester semesterEnum = (semester == null ? null : Semester.valueOf(semester) );
        
        List<Module> modules = moduleRepository.searchModules(title, credits, semesterEnum, academicYear);
        return ModuleMapper.toResponseList(modules);
    }


    public ModuleResponse registerModule(ModuleCreateRequest moduleRequest){
        if (moduleRepository.existsByCode(moduleRequest.getCode()))
            throw new ResourceAlreadyExistsException("Module code: '" + moduleRequest.getCode() + "' already exists");

        Module module = ModuleMapper.toModule(moduleRequest);
        moduleRepository.save(module);
        return ModuleMapper.toResponse(module);
    }


    @Transactional
    public ModuleResponse patchModule(Long id, ModulePatchRequest request){
        if (request.getTitle() == null && request.getCredits() == null && request.getSemester() == null && request.getAcademicYear() == null)
            throw new BadRequestException("At least one field must be given");
        
        Module module = moduleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Module with id " + id + " not found.")
        );

        if (request.getTitle() != null)
            module.setTitle(request.getTitle());
        if (request.getCredits() != null)
            module.setCredits(request.getCredits());
        if (request.getSemester() != null)
            module.setSemester(Semester.valueOf(request.getSemester()));
        if (request.getAcademicYear() != null)
            module.setAcademicYear(request.getAcademicYear());

        return ModuleMapper.toResponse(module);
    }


    public void deleteModuleById(Long id){
        if (!moduleRepository.existsById(id))
            throw new ResourceNotFoundException("Module with id " + id + " not found.");
        moduleRepository.deleteById(id);
    }

}
