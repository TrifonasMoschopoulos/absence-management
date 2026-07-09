package gr.techpro.absence.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload for partially updating an existing student. At least one field must be provided. All provided fields must be valid.")
public class StudentPatchRequest {
    
    @Size(min=2, max=50)
    @Pattern(regexp = ".*\\S.*")
    @Schema(description = "Must contain at least one non-whitespace character", example = "Johnny")
    private String firstName;
    
    @Size(min=2, max=50)
    @Pattern(regexp = ".*\\S.*")
    @Schema(description = "Must contain at least one non-whitespace character", example = "Depp")
    private String lastName;
    
    @Email
    @Size(min=5, max=255)
    @Schema(description = "Email must not already be in use and must be in valid email format", example = "johnny.depp@gmail.com")
    private String email;
    
    public StudentPatchRequest() { }

    public String getFirstName(){ return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email;}
}
