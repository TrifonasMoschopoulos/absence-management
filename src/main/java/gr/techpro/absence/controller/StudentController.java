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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.techpro.absence.api.StudentApi;
import gr.techpro.absence.dto.request.StudentCreateRequest;
import gr.techpro.absence.dto.request.StudentPatchRequest;
import gr.techpro.absence.dto.response.student.StudentResponse;
import gr.techpro.absence.service.StudentService;


@RestController
@RequestMapping("/api/students")
public class StudentController implements StudentApi{
    
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    
    
    @GetMapping("/{id}")
    @Override
    public StudentResponse getStudent(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    
    @GetMapping
    @Override
    public List<StudentResponse> getStudents(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return studentService.getFilteredStudents(firstName, lastName);
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public StudentResponse createStudent(@RequestBody StudentCreateRequest student){
        return studentService.registerStudent(student);
    }


    @PatchMapping("/{id}")
    @Override
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentPatchRequest request){
        return studentService.patchStudent(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }
}
