package gr.techpro.absence.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import gr.techpro.absence.api.ModuleApi;
import gr.techpro.absence.dto.request.ModuleCreateRequest;
import gr.techpro.absence.dto.request.ModulePatchRequest;
import gr.techpro.absence.dto.response.ModuleResponse;
import gr.techpro.absence.enums.Semester;
import gr.techpro.absence.service.ModuleService;


@RestController
@RequestMapping("/api/modules")
public class ModuleController implements ModuleApi{
    private final ModuleService moduleService;
    
    public ModuleController(ModuleService moduleService){
        this.moduleService = moduleService;
    }

    @GetMapping("/{id}")
    @Override
    public ModuleResponse getModule(@PathVariable Long id){
        return moduleService.getModuleById(id);
    }


    @GetMapping
    @Override
    public List<ModuleResponse> getModules(
            @RequestParam(required=false) String title, 
            @RequestParam(required=false) Integer credits, 
            @RequestParam(required=false) Semester semester, 
            @RequestParam(required=false) Integer academicYear
    ){
        return moduleService.getFilteredModules(title, credits, semester, academicYear);
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ModuleResponse createModule(@RequestBody ModuleCreateRequest module){
        return moduleService.registerModule(module);
    }


    @PatchMapping("/{id}")
    @Override
    public ModuleResponse updateModule(@PathVariable Long id, @RequestBody ModulePatchRequest request){
        return moduleService.patchModule(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteModule(@PathVariable Long id){
        moduleService.deleteModuleById(id);
    }

}
