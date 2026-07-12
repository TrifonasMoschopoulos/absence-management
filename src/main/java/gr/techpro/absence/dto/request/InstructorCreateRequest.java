package gr.techpro.absence.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description="Payload for creating a new Instructor. All fields are required")
public class InstructorCreateRequest {
    
    @NotBlank
    @Schema(description="Must contain at least one non-white character", example="Danny")
    private String firstName;

    @NotBlank
    @Schema(description="Must contain at least one non-white character", example="Washington")
    private String lastName;

    @Email
    @NotNull
    @Schema(description="Email must not already be in use and must be in valid email format", example="danny.was@gmail.com")
    private String email;    
    

    public InstructorCreateRequest() {}

    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }
}
