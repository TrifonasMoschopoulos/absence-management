package gr.techpro.absence.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Schema(description = "Payload for creating a new student. All fields are mandatory.")
public class StudentCreateRequest {

    @NotBlank
    @Size(min=2, max=50)
    @Schema(description = "Must contain at least one non-white character", example = "Johnny")
    private String firstName;
    
    @NotBlank
    @Size(min=2, max=50)
    @Schema(description = "Must contain at least one non-white character", example = "Depp")
    private String lastName;
    
    @Email
    @NotBlank
    @Size(min=5, max=255)
    @Schema(description = "Email must not already be in use and must be in valid email format", example = "johnny.depp@gmail.com")
    private String email;
    
    public StudentCreateRequest() { }

    public String getFirstName(){ return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email;}
    
}
