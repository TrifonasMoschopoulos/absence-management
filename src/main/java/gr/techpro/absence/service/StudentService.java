package gr.techpro.absence.service;

import java.util.List;
import java.time.Year;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.techpro.absence.entity.Student;
import gr.techpro.absence.dto.request.StudentCreateRequest;
import gr.techpro.absence.dto.request.StudentPatchRequest;
import gr.techpro.absence.dto.response.student.StudentResponse;
import gr.techpro.absence.exception.BadRequestException;
import gr.techpro.absence.exception.ResourceAlreadyExistsException;
import gr.techpro.absence.exception.ResourceNotFoundException;
import gr.techpro.absence.mapper.StudentMapper;
import gr.techpro.absence.repository.StudentRepository;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public StudentResponse getStudentById(Long id){
        Student student =  studentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Student with id " + id + " not found.")
        );

        return StudentMapper.toResponse(student);
    }

    public List<StudentResponse> getFilteredStudents(String firstName, String lastName){
        List<Student> students = studentRepository.searchStudents(firstName, lastName);
        return StudentMapper.toResponse(students);
    }
    
    
    public StudentResponse registerStudent(StudentCreateRequest request){
        if (studentRepository.existsByEmail(request.getEmail()))
            throw new ResourceAlreadyExistsException("Email is used.");
        
        Student student = StudentMapper.toStudent(request, generateStudentNumber());
        studentRepository.save(student);

        return StudentMapper.toResponse(student);
    }



    public void deleteStudentById(Long id){
        if (!studentRepository.existsById(id))
            throw new ResourceNotFoundException("Student with id " + id + " not found.");
        
        studentRepository.deleteById(id);
    }

    


    @Transactional
    public StudentResponse patchStudent(Long id, StudentPatchRequest request){
        if (request.getFirstName() == null && request.getLastName() == null && request.getEmail() == null)
            throw new BadRequestException("At least one attribute must be given."); 

        Student student = studentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Student with id " + id + " not found.")
        );
        if (request.getEmail() != null && !student.getEmail().equals(request.getEmail()) && studentRepository.existsByEmail(request.getEmail()))
            throw new ResourceAlreadyExistsException("New email given already exists.");
        
        
        if (request.getFirstName() != null)
            student.setFirstName(request.getFirstName());
        if (request.getLastName() != null)
            student.setLastName(request.getLastName());
        if (request.getEmail() != null)
            student.setEmail(request.getEmail());

        return StudentMapper.toResponse(student);
    }   
    

    
    
    private String generateStudentNumber(){
        String prefix = "STU" + Year.now().toString();;
        
        // Search the Database to find the max studentNumber, that starts with specific prefix
        String lastStudentNumber = studentRepository.findMaxStudentNumberByPrefix(prefix);
        if (lastStudentNumber == null){
            return prefix + "001";
        }

        // Extract suffix from the retrieved max studentNumber and construct the new suffix
        String oldSuffix = lastStudentNumber.substring(prefix.length(), lastStudentNumber.length());
        int newSuffix = Integer.parseInt(oldSuffix) + 1; 
        
        // Add padding (zeros) at the start of suffix in order to be at least 3 digits long
        String formattedSuffix = String.format("%03d", newSuffix);
        
        return prefix + formattedSuffix;
    }
    


}
