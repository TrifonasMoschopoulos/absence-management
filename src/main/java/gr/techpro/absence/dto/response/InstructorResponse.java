package gr.techpro.absence.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Instructor response containing all available information about an instructor")
public class InstructorResponse {

    @Schema(description="Surrogate key", example="101")
    private Long id;

    @Schema(example="Albert")
    private String firstName;

    @Schema(example="Einstein")
    private String lastName;

    @Schema(example="albert.einstein@gmail.com")
    private String email;

    public InstructorResponse() {}

    public InstructorResponse(Long id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() { return this.id; }
    public String getFirstname() { return this.firstName; }
    public String getLastname() { return this.lastName; }
    public String getEmail() { return this.email; }
}
